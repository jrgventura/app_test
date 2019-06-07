MVP 

Projecto usa el modelo MVP para separar funcionalidades por capas. 
Para cada funcionalidad de implementa su respectivo presentador, interactor. 

Login: 
Se utilizó Firebase-Auth (email / password), separado por capas según MVP

Registro: 
Se utilizó Firebase-Auth (email / password), separado por capas según MVP

Location Update:
Se utilizó un servicio que se ejecuta cada 30 min. 
Se pedirá el permiso al iniciar la pantalla home - listado de places 

Registro Places: 
Para almacenar la imagen se utilizó Firebase Cloud Storage
Para almacenar la data correspondiente al lugar se utilizó Cloud Firestore
El registro utiliza MVP

Listado Places:
Se utiliza Cloud Firestore para obtener la data y se muestra en un Recycler View utilizando Firebase UI Firestore

Se implementó DB y ORM para usuario y places usando Greendao v.3.2.2

