import { useRef, useState } from 'react'

function App() {
  // Referencia del <input/>
  const todoInput = useRef(null);

  // Lista de todos
  const [todos, setTodos] = useState([]);

  function createTodo(event) {
    event.preventDefault();

    const description = todoInput.current.value;

    setTodos([{ description, completed: false }, ...todos]);
  }

  function completeTodo(index) {
    return function (event) {
      event.preventDefault();
      const todo = todos[index];
      todo.completed = event.target.checked;
      setTodos([ ...todos ]);
    }
  }

  return (
    <div className="d-flex flex-column">
      <div className="input-group mb-3">
        <input type="text" className="form-control" ref={todoInput} />
        <button className="btn btn-outline-secondary" type="button" onClick={createTodo}>Button</button>
      </div>

      <div className="row">
        {
          todos.map((todo, index) => (
            <div className="col-sm-6 mb-3 mb-sm-0">
              <div className="card">
                <div className="card-body">
                  <p className="card-text">{todo.description}</p>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value={todo.completed} id="checkDefault" onChange={completeTodo(index)} />
                    <label className="form-check-label" for="checkDefault">
                      { todo.completed ? 'Cancelar' : 'Completar'}
                    </label>
                  </div>
                </div>
              </div>
            </div>
          ))
        }
      </div>
    </div>
  )
}

export default App
