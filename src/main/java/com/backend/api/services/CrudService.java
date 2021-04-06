package com.backend.api.services;

import com.backend.api.domain.Base;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;


public interface CrudService<Bean extends Base, CreateDto, ReadDto, UpdateDto, FilterDto extends Filter> {
    Bean create(CreateDto createDto);

    void delete(Long id);

    Bean findById(Long id);

    void update(Long id, UpdateDto updateDto);

    Page<ReadDto> findAll(Pageable pageable);

    default Page<ReadDto> findAll(Pageable pageable, FilterDto filter) {
        return findAll(pageable);
    }

    Bean toEntity (CreateDto dto);

//    @Autowired
//    public JpaRepository<Bean, Integer> repo;
//
//    @Autowired
//    public JpaSpecificationExecutor<Bean> executor;
//
//    @Autowired
//    protected HttpServletRequest request;
//
//    protected Class<Bean> beanClass;
//
//    public CrudService(final Class<Bean> beanClass, final Class<CreateDto> dtoClass) {
//        this.dtoClass = dtoClass;
//        this.beanClass = beanClass;
//    }
//
//    public Bean update(final Bean obj) {
//        final Bean newObj = find(obj.getId());
//        newObj.clone(obj);
//        return repo.save(newObj);
//    }
//
//    public Bean find(final Long id) {
//        return this.repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Item não encontrado."));
//    }
//
//    public Bean insert(final Bean obj) {
//        obj.setId(null);
//        try {
//            return this.repo.save(obj);
//
//        } catch (ConstraintViolationException e) {
//            throw new DataIntegrityException(
//                    "Erro ao salvar registro no sistema, algum campo está preenchido incorretamente");
//        }
//    }
//
//    public void delete(final Long id) {
//        this.repo.deleteById(id);
//
//    }
//
//    private Page<CreateDto> findPage(final Searchable searchable, CrudSpecificationBuilder<Bean> builder) {
//        final String search = searchable.getSearch();
//
//        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|=|%)(\\w+?)(,|\\|)");
//        Matcher matcher = pattern.matcher(search + ",");
//
//        while (matcher.find()) {
//            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
//        }
//
//        Specification<Bean> spec = builder.build();
//        Page<Bean> list = executor.findAll(spec, searchable.getPageable());
//
//        return list.map(this::toDTO);
//    }
//
//    public Page<CreateDto> findPage(final Searchable searchable, List<Bean> notIn, String key) {
//        CrudSpecificationBuilder<Bean> builder = new CrudSpecificationBuilder<>();
//        builder.with(notIn, key);
//        return findPage(searchable, builder);
//    }
//
//    public Page<CreateDto> findPage(final Searchable searchable) {
//        CrudSpecificationBuilder<Bean> builder = new CrudSpecificationBuilder<>();
//        return findPage(searchable, builder);
//    }
//
//    public List<Bean> findAll() {
//        return repo.findAll();
//    }

}