import { useState } from "react";
import api from "../api";

function Checkout() {
  const [order, setOrder] = useState({
    customerName: "",
    address: "",
    paymentMethod: "UPI",
    totalAmount: ""
  });

  const handleChange = (e) => {
    setOrder({ ...order, [e.target.name]: e.target.value });
  };

  const placeOrder = async () => {
    await api.post("/order", order);
    alert("Fruit Order Placed Successfully!");
  };

  return (
    <div className="card">
      <h2 className="heading">Checkout / Place Order</h2>
      <div className="checkout-grid">
        <input className="form-control" name="customerName" placeholder="Customer Name" onChange={handleChange} />
        <input className="form-control" name="address" placeholder="Delivery Address" onChange={handleChange} />
        <select className="form-control" name="paymentMethod" onChange={handleChange}>
          <option value="UPI">UPI</option>
          <option value="Card">Card</option>
          <option value="COD">Cash on Delivery</option>
        </select>
        <input className="form-control" name="totalAmount" placeholder="Total Amount (₹)" onChange={handleChange} />
      </div>
      <button className="btn btn-success" onClick={placeOrder}>Place Order</button>
    </div>
  );
}

export default Checkout;