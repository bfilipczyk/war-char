import './App.css';
import Login from './login/Login';
import Register from "./register/Register";
import User from "./user/User";
import Armor from "./armor/Armor";
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route path="/user/:userId" component={User} />
        <Route exact path="/armor" component={Armor} />
      </Switch>
    </Router>
  );
}

export default App;
