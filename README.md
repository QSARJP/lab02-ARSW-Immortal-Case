# Compile and run instructions

## Para ejecutar Prodcons: 
mvn exec:java -Dexec.mainClass="edu.eci.arst.concprg.prodcons.StartProduction"

## Para ejecutar highlandersim: 
mvn exec:java -Dexec.mainClass="edu.eci.arsw.highlandersim.ControlFrame"

# Part I - Before finishing class 

1. El resuiltado de ejecutar el programa es el siguiente:
![](img/cpu1.jpg)

La clase responsalbe de ese consumo es  consumer debido a que pregunta a cada instante si hay algun elemento en la cola y como se tiene un productor demorado con respecto al consumidor se genera ese consumo elevado.

2. Para mejorar el rendimiento es necesario que el productor avise al consumidor que ya hay algo en la lista para que el pueda recibirlo.
Consumidor:
![](img/cpu2Consumer.jpg)
Productor:
![](img/cpu2Producer.jpg)
y el rendimeinto se puede ver que diminuye considerablemente.
![](img/cpu2.jpg)

3.ahora al colocar el consumidor mas lento que el productor, debemos implementar que el consumidor pueda comenzar hasta que se llene la lista, despues de que se llene la primera vez el productor va a a intentar llenar la lista y el consumidor va a tomar un poco mas lento.

el rendimiento es el sigueinte:

![](img/cpu3100.jpg)

# Part II - INMORTAL

2.  El invariante depende estrictamente del numero de jugadores y se veria de la sigueinte manera: N*Default_Inmortal_Health

3. Al implemetarse por primera vez paused and check no se cumple el invariante ya que la suma cambia en diferentes momentos.
primer momento:
![](img/3Parte2.jpg)
segundo momento:
![](img/3Parte2-1.jpg)


5. No se sigue cumpliendo el invariante 

primer momento:
![](img/5Parte2.jpg)
segundo momento:
![](img/5Parte2-3.jpg)


7. El problema se paro por que se genera un DeadLock debido a que en un momento varios inmortales estan en espera y ninguno manda la señal para que pueda seguir la operacion.



