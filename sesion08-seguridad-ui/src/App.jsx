import { useRef, useState, useEffect } from 'react'
import { useNavigate } from 'react-router';

function useFetch(navigate) {
 return async function (url, params) {
  const token = localStorage.getItem("$token");

  const response = await fetch(url, {
    ...params,
    headers: {
      ...params?.headers,
      'Authorization': 'Bearer ' + token // Usando el token en la petición
    }
  });

  if (response.status === 403) {
    navigate("/login")
    throw new Error("Su sesión ha expirado, inicie sesión nuevamente");
  }

  return response;
 }
}

function App() {
  // Referencia del <input/>
  const todoInput = useRef(null);

  // Lista de todos
  const [todos, setTodos] = useState([]);

  const navigate = useNavigate();
  const fetchWithAuth = useFetch(navigate);

  function logout() {
    localStorage.removeItem("$token");
    navigate("/login");
  }

  // Cuando cargue el compente, llamamos al backend
  useEffect(() => {

    async function findAll() {
      try {
        // Hacemos la petición y recibimos respuesta
        const response = await fetchWithAuth("http://localhost:8080/todos");

        // Extraemos el JSON de la respuesta
        const data = await response.json();

        setTodos(data); // Alimentando el estado con lo que viene del backend
      } catch (err) {
        alert("Ocurrió un error: " + err.message);
      }
    }

    findAll();

  }, []);

  async function createTodo(event) {
    event.preventDefault();

    const description = todoInput.current.value.trim();

    if (description) {
      // En fetch, el request body tiene que ir en un string
      // En headers.Content-Type especificamos que es un JSON
      try {
        const response = await fetchWithAuth("http://localhost:8080/todos", {
          method: "POST",
          body: JSON.stringify({ description }),
          headers: {
            "Content-Type": "application/json"
          }
        });

        const data = await response.json();

        setTodos([ ...todos, data ]);
      } catch (err) {
        alert("Ocurrió un error: " + err.message);
      }      
    }

    todoInput.current.value = "";
  }

  function completeTodo(id) {
    return async function () {
      try {
        // Fetch por defecto hace peticiones GET
        // a menos de que se especifique lo contrario
        const response = await fetchWithAuth("http://localhost:8080/todos/" + id, {
          method: "PUT"
        });

        if (response.ok) {
          const todo = todos.find(t => t.id === id);
          todo.completed = !todo.completed;
          setTodos([ ...todos ]);
        }
      } catch (err) {
        alert("Ocurrió un error: " + err.message);
      }
    }
  }

  return (
    <div className="vh-100 vw-100">
      <nav className="navbar bg-dark navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">TODO APP</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <a className="nav-link" href="#" onClick={logout}>Cerrar sesión</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <div className="container p-5 justify-content-center">
        <div className="input-group mb-3">
          <input type="text" className="form-control" ref={todoInput} />
          <button className="btn btn-primary" type="button" onClick={createTodo}>Agregar</button>
        </div>

        <div className="row row-gap-3">
          {
            todos.map((todo, index) => (
              <div className="col-sm-6 mb-3 mb-sm-0" key={index}>
                <div className="card">
                  <div className="card-body">
                    <p className={ todo.completed ? "card-text text-decoration-line-through" : "card-text" }>{todo.description}</p>
                    <div className="row justify-content-between px-2">
                      <div className="form-check col-6">
                        <input className="form-check-input" type="checkbox" id="checkDefault" checked={todo.completed} onChange={completeTodo(todo.id)} />
                        <label className="form-check-label" htmlFor="checkDefault">
                          { todo.completed ? 'Cancelar' : 'Completar'}
                        </label>
                      </div>
                      <div className="col-6">
                        <button className="btn btn-sm btn-danger">Eliminar</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            ))
          }
        </div>
      </div>
    </div>
  )
}

export default App
