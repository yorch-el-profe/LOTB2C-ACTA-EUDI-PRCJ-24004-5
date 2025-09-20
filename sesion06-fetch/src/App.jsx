import { useRef, useState } from 'react'

function App() {
  // Referencia del <input/>
  const todoInput = useRef(null);

  // Lista de todos
  const [todos, setTodos] = useState([]);

  function createTodo(event) {
    event.preventDefault();

    const description = todoInput.current.value.trim();

    if (description) {
      setTodos([{ description, completed: false }, ...todos]);
    }

    todoInput.current.value = "";
  }

  function completeTodo(index) {
    return function (event) {
      const todo = todos[index];
      todo.completed = event.target.checked;
      setTodos([ ...todos ]);
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
                        <input className="form-check-input" type="checkbox" id="checkDefault" checked={todo.completed} onChange={completeTodo(index)} />
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
