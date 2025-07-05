<style>
body {
  font-family: Spectral, "Gentium Basic", Cardo , "Linux Libertine o", "Palatino Linotype", Cambria, serif;
  font-size: 100% !important;
  padding-right: 12%;
}
code {
  padding: 0.25em;
	
  white-space: pre;
  font-family: "Tlwg mono", Consolas, "Liberation Mono", Menlo, Courier, monospace;
	
  background-color: #ECFFFA;
  //border: 1px solid #ccc;
  //border-radius: 3px;
}

kbd {
  display: inline-block;
  padding: 3px 5px;
  font-family: "Tlwg mono", Consolas, "Liberation Mono", Menlo, Courier, monospace;
  line-height: 10px;
  color: #555;
  vertical-align: middle;
  background-color: #ECFFFA;
  border: solid 1px #ccc;
  border-bottom-color: #bbb;
  border-radius: 3px;
  box-shadow: inset 0 -1px 0 #bbb;
}

h1,h2,h3,h4,h5 {
  color: #269B7D; 
  font-family: "fira sans", "Latin Modern Sans", Calibri, "Trebuchet MS", sans-serif;
}

</style>

# Flyway integration

## Purpose
For production like databases, we would like to use flyway, to maintain a consistent database state. This is not 
mentioned in the book, but we would like to implement it somehow anyway.

## Sources

### Combining `quarkus-hibernate-orm-panache` with `quarkus-flyway`
- [https://stephennimmo.com/2024/03/08/build-a-rest-api-from-the-ground-up-with-quarkus-3-0/](https://stephennimmo.com/2024/03/08/build-a-rest-api-from-the-ground-up-with-quarkus-3-0/)
- [https://edwin.baculsoft.com/2023/10/a-multi-tenant-application-using-quarkus-flyway-and-openshift-4/](https://edwin.baculsoft.com/2023/10/a-multi-tenant-application-using-quarkus-flyway-and-openshift-4/)

### Flyway
- [https://quarkus.io/extensions/?search-regex=flyway](https://quarkus.io/extensions/?search-regex=flyway)
- [https://quarkus.io/extensions/io.quarkus/quarkus-flyway/](https://quarkus.io/extensions/io.quarkus/quarkus-flyway/)
- [https://quarkus.io/extensions/io.quarkus/quarkus-flyway-postgresql/](https://quarkus.io/extensions/io.quarkus/quarkus-flyway-postgresql/)
- [https://quarkus.io/guides/#q=flyway](https://quarkus.io/guides/#q=flyway)
- [https://quarkus.io/guides/flyway](https://quarkus.io/guides/flyway)

### Panache
- [https://thorben-janssen.com/introduction-panache/](https://thorben-janssen.com/introduction-panache/)
- [https://quarkus.io/guides/datasource](https://quarkus.io/guides/datasource)
- [https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-orm-panache/](https://quarkus.io/extensions/io.quarkus/quarkus-hibernate-orm-panache/)
- [https://quarkus.io/guides/hibernate-orm-panache](https://quarkus.io/guides/hibernate-orm-panache)

### Configurations
- [https://quarkus.io/guides/config-reference](https://quarkus.io/guides/config-reference)
- [https://quarkus.io/guides/all-config](https://quarkus.io/guides/all-config)
