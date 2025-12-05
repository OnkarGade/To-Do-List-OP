import ReactDOM from 'react-dom/client';
import Home from './pages/Home';
import './index.css';
import store from './store';
import {Provider} from 'react-redux';
const root = ReactDOM.createRoot(document.querySelector("#root"));
root.render(
    <Provider store={store}>
        <Home/>
    </Provider>
);