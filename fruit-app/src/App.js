import { useEffect, useState } from "react";
import api from "./api";
import "./styles/App.css";

import AddFruit from "./components/AddFruit";
import FruitList from "./components/FruitList";
import SearchBar from "./components/SearchBar";
import Checkout from "./components/Checkout";

function App() {
  const [fruits, setFruits] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [editFruit, setEditFruit] = useState(null);

  useEffect(() => {
    loadFruits();
  }, []);

  const loadFruits = async () => {
    const res = await api.get("/fruits");
    setFruits(res.data);
  };

  const searchFruits = async () => {
    const res = await api.get(`/search?keyword=${keyword}`);
    setFruits(res.data);
  };

  const updateFruit = async () => {
    await api.put(`/fruit/${editFruit.id}`, editFruit);
    alert("Fruit Details Updated");
    loadFruits();
    setEditFruit(null);
  };

  return (
    <div className="app-container">
      <div className="navbar">Fruit & Order Management System</div>

      <AddFruit loadFruits={loadFruits} />

      <SearchBar
        keyword={keyword}
        setKeyword={setKeyword}
        searchFruits={searchFruits}
        loadFruits={loadFruits}
      />

      {editFruit && (
        <div className="card">
          <h2 className="heading">Update Fruit</h2>
          <input
            className="form-control"
            value={editFruit.name}
            onChange={(e) => setEditFruit({ ...editFruit, name: e.target.value })}
          />
          <input
            className="form-control"
            value={editFruit.pricePerKg}
            onChange={(e) => setEditFruit({ ...editFruit, pricePerKg: e.target.value })}
          />
          <button className="btn btn-warning" onClick={updateFruit}>
            Update Fruit
          </button>
        </div>
      )}

      <FruitList
        fruits={fruits}
        loadFruits={loadFruits}
        setEditFruit={setEditFruit}
      />

      <Checkout />
    </div>
  );
}

export default App;
