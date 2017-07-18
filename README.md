# Mybatis-basic
## mybatis-config.xml
- properties
- settings
- typeAlias
- typeHandlers
- plugins
- environments
- databaseIdProvider
- mappers
## CRUD for MySQL
- useGeneratedKeys + keyProperty, selectKey for Oracle
- multiple parameter from method, #{param1}...#{paramN}, @Param, POJO, Map, TO(Transfer Object)
- resultType: element type for List, map(alias) for Map(one Object), element type for Map(multiple Objects)@MapKey
- resultMap: 
  - cascade query, property="className.propertyName"
  - many-to-one: association: javaType, step by step: select, column, lazy loading
  - one-to-many: collection: ofType, fetchType
- discriminator: javaType, column, case
## Dynamic SQL
- \<if> \</if> 
- \<where> \</where>
- \<trim> \</trim>
- \<choose> \</choose> \<when> \</when> \<otherwise> \</otherwise>
- \<foreach> \</foreach>
- \<bind>, \<sql> \</sql>, \<include> \</include>
## Cache
- first-level cache
- second-level cache(global cache)
- \<cache> \</cache>, useCache, flushCache
- ehcache
## Generator
## PageHelper
## Batch operation
sqlSessionFactory.openSession(ExecutorType.BATCH)
## Self-defined TypeHandler for enum
