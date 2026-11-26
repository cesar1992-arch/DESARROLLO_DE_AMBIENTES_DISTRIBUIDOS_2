from locust import User, task, between, events
import socket
import time


class ChatUser(User):

    wait_time = between(1, 2)

    @task
    def enviar_mensaje(self):

        inicio = time.time()

        try:

            s = socket.socket(
                socket.AF_INET,
                socket.SOCK_STREAM
            )

            s.settimeout(5)

            s.connect(("localhost", 5000))

            mensaje = (
                "Locust: mensaje de prueba\n"
            )

            s.sendall(mensaje.encode())

            tiempo = int(
                (time.time() - inicio) * 1000
            )

            events.request.fire(
                request_type="TCP",
                name="EnviarMensaje",
                response_time=tiempo,
                response_length=len(mensaje),
                exception=None,
            )

            s.close()

        except Exception as e:

            tiempo = int(
                (time.time() - inicio) * 1000
            )

            events.request.fire(
                request_type="TCP",
                name="EnviarMensaje",
                response_time=tiempo,
                response_length=0,
                exception=e,
            )