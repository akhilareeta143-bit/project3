import api from "../api";

function FruitList({ fruits, loadFruits, setEditFruit }) {
  const deleteFruit = async (id) => {
    await api.delete(`/fruit/${id}`);
    alert("Fruit Deleted");
    loadFruits();
  };

  return (
    <div className="card">
      <h2 className="heading">Available Fruits</h2>
      <div className="product-grid">
        {fruits.map((f) => (
          <div className="product-card" key={f.id}>
            <img
              className="product-image"
              src={`http://localhost:8080/api/fruit/${f.id}/image`}
              alt={f.name}
            />
            <div className="product-content">
              <h3>{f.name}</h3>
              <p>{f.description}</p>
              <p><strong>Origin:</strong> {f.origin}</p>
              <p><strong>Category:</strong> {f.category}</p>
              <p className="price">₹ {f.pricePerKg} / kg</p>
              <button className="btn btn-warning" onClick={() => setEditFruit(f)}>Edit</button>
              <button className="btn btn-danger" onClick={() => deleteFruit(f.id)}>Delete</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default FruitList;
