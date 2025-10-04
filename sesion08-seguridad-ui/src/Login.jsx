import { useRef } from "react";
import { useNavigate } from "react-router";

function Login() {
    const usernameRef = useRef(null);
    const passwordRef = useRef(null);
    const formRef = useRef(null);
    const navigate = useNavigate();

    async function submit(event) {
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/auth/login', {
                method: "POST",
                body: JSON.stringify({ 
                    username: usernameRef.current.value,
                    password: passwordRef.current.value
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.status === 403) {
                throw new Error("El usuario o la contraseña es inválido");
            }

            const token = await response.text();

            localStorage.setItem("$token", token);
            navigate("/"); // Redirigimos al usuario a la página principal
        } catch (err) {
            alert('Ocurrio un error al iniciar sesión: ' + err.message);
        }
    }

    function validateForm() {
        formRef.current.classList.add('was-validated');
    }

    return (
        <div className="vh-100 vw-100 p-5">
            <div className="container justify-content-center">
                <h2 className="mb-3">Inicio de Sesión</h2>
                <form ref={formRef} onSubmit={submit}>
                    <div className="mb-3">
                        <label htmlFor="exampleFormControlInput1" className="form-label">Nombre de usuario</label>
                        <input type="text" className="form-control" id="exampleFormControlInput1" ref={usernameRef} required />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="exampleFormControlInput2" className="form-label">Contraseña</label>
                        <input type="password" className="form-control" id="exampleFormControlInput2" ref={passwordRef} required />
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={validateForm}>Iniciar</button>
                </form>
            </div>
        </div>
    )
}

export default Login;