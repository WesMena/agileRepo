Configuración inicial

1.Correr el script "AgileRepoBD.sql" en MySQL Workbench para crear
el esquema de base de datos
2.Abrir el proyecto en Netbeans y agregar las librerías 
3.Ajustar el url de la conexión a la base de datos, esto se hace en la
clase "Conexion" del paquete com.cci.service
4.En la clase "EventWizardImagesController" ubicada en el paquete 
com.cci.controller, debe sustituirse el directorio definido en la variable 
"output" del método "save"
5.En la clase "portadaController" ubicada en el paquete com.cci.controller
debe sustituirse a primera parte de la variable "directorioGuardado" por 
el directorio en que se colocó el proyecto de Netbeans