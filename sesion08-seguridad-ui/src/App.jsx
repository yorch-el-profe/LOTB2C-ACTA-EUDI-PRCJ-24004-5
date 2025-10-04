import { useRef, useState, useEffect } from 'react'

function App() {
  // Referencia del <input/>
  const todoInput = useRef(null);

  // Lista de todos
  const [todos, setTodos] = useState([]);

  // Cuando cargue el compente, llamamos al backend
  useEffect(() => {

    async function findAll() {
      try {
        // Hacemos la petición y recibimos respuesta
        const response = await fetch("http://localhost:8080/todos");

        console.log(response);

        // Extraemos el JSON de la respuesta
        //const data = await response.json();

        //setTodos(data); // Alimentando el estado con lo que viene del backend
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
        const response = await fetch("http://localhost:8080/todos", {
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
        const response = await fetch("http://localhost:8080/todos/" + id, {
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
    <div className="vh-100 vw-100 p-5">
      <div className="container justify-content-center">
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
