package com.backend.api.services;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.backend.api.domain.Base;
import com.backend.api.services.exceptions.DataIntegrityException;
import com.backend.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<Bean extends Base, DTO> {

    @Autowired
    protected JpaRepository<Bean, Integer> repo;

    @Autowired
    protected HttpServletRequest request;

    public abstract Bean fromDTO(DTO obj);

    public abstract DTO toDTO(Bean obj);

    protected Class<DTO> dtoClass;

    protected Class<Bean> beanClass;

    public CrudService(final Class<Bean> beanClass, final Class<DTO> dtoClass) {
        this.dtoClass = dtoClass;
        this.beanClass = beanClass;
    }

    public Bean update(final Bean obj) {
        final Bean newObj = find(obj.getId());
        newObj.clone(obj);
        return repo.save(newObj);
    }

    public Bean find(final Integer id) {
        final Bean obj = this.repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Item não encontrado."));
        return obj;
    }

    public Bean insert(final Bean obj) {
        obj.setId(null);
        return this.repo.save(obj);
    }

    public void delete(final Integer id) {
        this.repo.deleteById(id);

    }

    public Page<Bean> findPage(final Integer page, final Integer linesPerPage, final String orderBy,
            final String direction) {
        final PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        try {
            final Example<Bean> e = createExampleObject();
            return repo.findAll(e, pageRequest);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | NoSuchFieldException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Bean> findAll() {
        return repo.findAll();
    }

    /**
     * 
     * @return An example object created based on request filters
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws UnsupportedEncodingException
     */
    protected Example<Bean> createExampleObject()
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, NoSuchFieldException, UnsupportedEncodingException {

        final List<String> fieldsToFilter = getFieldsToFilter();
        final List<String> classFields = getClassFields();
        final Bean obj = beanClass.getDeclaredConstructor().newInstance();

        ExampleMatcher em = ExampleMatcher.matchingAll().withIgnoreCase();
        for (final String field : fieldsToFilter) {
            if (classFields.indexOf(field) == -1) {
                throw new DataIntegrityException("Campo (" + field + ") do filtro não encontrado");
            } else {
                em = em.withMatcher(field, ExampleMatcher.GenericPropertyMatchers.startsWith());
                final String setter = fieldToEncapsulator(field, "set");
                final String value = new String(request.getParameter("filterBy" + field).getBytes("UTF-8"), "ASCII"); // Decodes the data sended
                final Class<?> valueClass = dtoClass.getDeclaredField(field).getType();
                final Object parameterValue = valueClass.getDeclaredConstructor(valueClass).newInstance(value);
                obj.getClass().getMethod(setter, valueClass).invoke(obj, parameterValue);
            }
        }
        return Example.of(obj, em);
    }

    /**
     * 
     * @return A list the class fields
     */
    public List<String> getClassFields() {
        final List<String> fields = new ArrayList<>();

        for (final Field f : dtoClass.getDeclaredFields()) {
            if (!f.getName().equals("serialVersionUID")) {
                fields.add(f.getName());
            }
        }
        return fields;
    }

    /**
     * 
     * @param encap if its a getter (get) or a setter (set)
     * @return the getter or setter method from a field in a class
     */
    protected List<String> getClassEncapsulators(final String encap) {
        if (Arrays.asList("get", "set").indexOf(encap) == -1) {
            throw new RuntimeException("Apenas getters e setters são permitidos");
        }
        final List<String> classGetters = new ArrayList<>();

        for (final Field f : dtoClass.getDeclaredFields()) {
            if (!f.getName().equals("serialVersionUID")) {
                final String getter = fieldToEncapsulator(f, encap);
                classGetters.add(getter);
            }
        }

        return classGetters;
    }

    /**
     * 
     * @return A list of fields sended by the request to be filtered
     */
    private List<String> getFieldsToFilter() {
        final List<String> fields = new ArrayList<>();
        request.getParameterNames().asIterator().forEachRemaining(param -> {
            final String staticFilter = "filterBy";
            if (param.contains(staticFilter))
                fields.add(param.replace(staticFilter, ""));
        });
        return fields;
    }

    /**
     * Generates a method string name by a field and a prefix
     * 
     * @param field
     * @param encap
     * @return
     */
    private String fieldToEncapsulator(final Field field, final String encap) {
        return encap + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
    }

    /**
     * Generates a method string name by a field and a prefix
     * 
     * @param field
     * @param encap
     * @return
     */
    private String fieldToEncapsulator(final String field, final String encap) {
        return encap + Character.toUpperCase(field.charAt(0)) + field.substring(1);
    }

}