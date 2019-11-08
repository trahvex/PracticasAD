Unos enlaces para el upload con REST.

Configuración: 

https://stackoverflow.com/questions/30653012/multipart-form-data-no-injection-source-found-for-a-parameter-of-type-public-ja

De todo lo que dice este post, los pasos de web.xml no son necesarios pero el registro en ApplicationConfig.java sí.

Código ejemplo: 

http://www.mkyong.com/webservices/jax-rs/file-upload-example-in-jersey/

Aquí podéis descargar el código de ejemplo de cómo implementar la operación en el servicio.

La librería que tenéis que añadir a Glassfish es jersey-media-multipart-2.17.jar, no hace falta ninguna más y no se "pelea" con las que tiene Glassfish.

La podéis encontrar en https://mvnrepository.com/artifact/org.glassfish.jersey.media/jersey-media-multipart/2.17






Tutorial de cómo enviar ficheros binarios en SOAP con Netbeans

https://netbeans.org/kb/docs/websvc/flower_overview.html
