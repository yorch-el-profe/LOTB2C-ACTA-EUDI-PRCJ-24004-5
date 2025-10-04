import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { BrowserRouter, Route, Routes } from 'react-router';

// Integración de Bootstrap con NPM
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './Login.jsx';
import Register from './Register.jsx';

createRoot(document.getElementById('root'))
    .render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<App />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    </BrowserRouter>);
