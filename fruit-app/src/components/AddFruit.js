

import { useState } from "react";
import api from "../api";

function AddFruit({ loadFruits }) {
  const [fruit, setFruit] = useState({
    name: "",
    description: "",
    origin: "",
    category: "",
    pricePerKg: ""
  });

  const [image, setImage] = useState(null);

  const handleChange = (e) => {
    setFruit({ ...fruit, [e.target.name]: e.target.value });
  };

  const saveFruit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append(
      "fruit",
      new Blob([JSON.stringify(fruit)], { type: "application/json" })
    );
    formData.append("image", image);

    await api.post("/fruit", formData);
    alert("Fruit Added Successfully!");
    loadFruits();
  };

  return (
    <div className="card">
      <h2 className="heading">Add Fresh Fruit</h2>
      <input className="form-control" name="name" placeholder="Fruit Name (e.g. Apple)" onChange={handleChange} />
      <input className="form-control" name="description" placeholder="Description" onChange={handleChange} />
      <input className="form-control" name="origin" placeholder="Origin (e.g. Kashmir)" onChange={handleChange} />
      <input className="form-control" name="category" placeholder="Category (e.g. Tropical)" onChange={handleChange} />
      <input className="form-control" type="number" name="pricePerKg" placeholder="Price Per Kg (₹)" onChange={handleChange} />
      <input className="form-control" type="file" onChange={(e) => setImage(e.target.files[0])} />
      <button className="btn btn-success" onClick={saveFruit}>Save Fruit</button>
    </div>
  );
}

export default AddFruit;