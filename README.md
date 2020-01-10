# Call certer almundo

## Tests
Los tests pueden correrse utilizando el comando de maven "mvn test".

## Extras/Plus
Si el dispatcher no puede asignar la llamada, la pone en espera agregandola a una cola fifo.
Cuando un empleado termina su llamada, se lo libera para que el dipatcher lo pueda volver a asigar y, en caso de tener llamadas en espera, se le asigna la linea liberada a la llamada que esta hace mas tiempo esperando.

En la clase disparcher se agregaron comentarios en los metodos para un mejor entendimiento del codigo.