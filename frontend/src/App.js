import './App.css';
import Login from './login/Login';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
      </Switch>
    </Router>
  );
}

export default App;
