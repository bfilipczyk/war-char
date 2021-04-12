import './App.css';
import Login from './login/Login';
import Register from "./register/Register";
import User from "./user/User";
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/register" component={Register} />
        <Route path="/user/:userId" component={User} />
      </Switch>
    </Router>
  );
}

export default App;
