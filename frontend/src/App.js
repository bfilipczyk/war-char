import './App.css';

import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import Login from "./login/Login";
import Register from "./register/Register";
import Home from "./home/Home";
import Armor from "./armor/Armor";
import Weapon from "./weapon/Weapon";
import CharacterPage from "./characterPage/CharacterPage";
import Skill from "./skill/Skill";
import Talent from "./talent/Talent";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/home" component={Home} />
        <Route exact path="/armor" component={Armor} />
        <Route exact path="/weapon" component={Weapon} />
        <Route exact path="/skills" component={Skill} />
        <Route exact path="/talents" component={Talent} />
        <Route path="/characters/:characterId" component={CharacterPage} />
      </Switch>
    </Router>
  );
}

export default App;
