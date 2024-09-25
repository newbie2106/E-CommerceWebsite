import React, { useState } from "react";
import { FaChevronDown, FaChevronUp, FaTimes, FaCreditCard, FaPaypal, FaApplePay } from "react-icons/fa";

const OrderDetailsPage = () => {
  const [items, setItems] = useState([
    { id: 1, name: "Product 1", quantity: 2, price: 19.99, image: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80" },
    { id: 2, name: "Product 2", quantity: 1, price: 29.99, image: "https://images.unsplash.com/photo-1542291026-7eec264c27ff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80" },
  ]);

  const [expandedItems, setExpandedItems] = useState([]);
  const [shippingInfo, setShippingInfo] = useState({
    name: "",
    addressLine1: "",
    addressLine2: "",
    city: "",
    country: "",
    state: "",
    postalCode: "",
    phone: "",
  });
  const [shippingMethod, setShippingMethod] = useState("standard");
  const [paymentInfo, setPaymentInfo] = useState({
    cardNumber: "",
    expirationDate: "",
    cvv: "",
  });
  const [paymentMethod, setPaymentMethod] = useState("creditCard");
  const [errors, setErrors] = useState({});

  const toggleItemExpansion = (id) => {
    setExpandedItems((prev) =>
      prev.includes(id) ? prev.filter((itemId) => itemId !== id) : [...prev, id]
    );
  };

  const removeItem = (id) => {
    setItems((prev) => prev.filter((item) => item.id !== id));
  };

  const handleShippingInfoChange = (e) => {
    const { name, value } = e.target;
    setShippingInfo((prev) => ({ ...prev, [name]: value }));
  };

  const handlePaymentInfoChange = (e) => {
    const { name, value } = e.target;
    setPaymentInfo((prev) => ({ ...prev, [name]: value }));
  };

  const validateForm = () => {
    const newErrors = {};
    if (!shippingInfo.name) newErrors.name = "Name is required";
    if (!shippingInfo.addressLine1) newErrors.addressLine1 = "Address is required";
    if (!shippingInfo.city) newErrors.city = "City is required";
    if (!shippingInfo.country) newErrors.country = "Country is required";
    if (!shippingInfo.state) newErrors.state = "State is required";
    if (!shippingInfo.postalCode) newErrors.postalCode = "Postal code is required";
    if (!shippingInfo.phone) newErrors.phone = "Phone number is required";

    if (paymentMethod === "creditCard") {
      if (!paymentInfo.cardNumber) newErrors.cardNumber = "Card number is required";
      if (!paymentInfo.expirationDate) newErrors.expirationDate = "Expiration date is required";
      if (!paymentInfo.cvv) newErrors.cvv = "CVV is required";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      // Process the order
      console.log("Order submitted");
    }
  };

  const totalCost = items.reduce((sum, item) => sum + item.quantity * item.price, 0);

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8">Order Details</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-2xl font-semibold mb-4">Order Summary</h2>
          {items.map((item) => (
            <div key={item.id} className="mb-4 border-b pb-4">
              <div className="flex items-center justify-between">
                <div className="flex items-center">
                  <img src={item.image} alt={item.name} className="w-16 h-16 object-cover rounded mr-4" />
                  <div>
                    <h3 className="font-semibold">{item.name}</h3>
                    <p className="text-gray-600">
                      {item.quantity} x ${item.price.toFixed(2)}
                    </p>
                  </div>
                </div>
                <div className="flex items-center">
                  <button
                    onClick={() => toggleItemExpansion(item.id)}
                    className="text-gray-500 hover:text-gray-700 mr-2"
                    aria-label={expandedItems.includes(item.id) ? "Collapse item details" : "Expand item details"}
                  >
                    {expandedItems.includes(item.id) ? <FaChevronUp /> : <FaChevronDown />}
                  </button>
                  <button
                    onClick={() => removeItem(item.id)}
                    className="text-red-500 hover:text-red-700"
                    aria-label="Remove item"
                  >
                    <FaTimes />
                  </button>
                </div>
              </div>
              {expandedItems.includes(item.id) && (
                <div className="mt-4 text-gray-600">
                  <p>Additional details about the product can go here.</p>
                </div>
              )}
            </div>
          ))}
          <div className="mt-6 text-xl font-semibold">
            Total: ${totalCost.toFixed(2)}
          </div>
        </div>
        <div>
          <form onSubmit={handleSubmit}>
            <div className="bg-white p-6 rounded-lg shadow-md mb-8">
              <h2 className="text-2xl font-semibold mb-4">Shipping Information</h2>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label htmlFor="name" className="block text-sm font-medium text-gray-700">
                    Full Name
                  </label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    value={shippingInfo.name}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
                </div>
                <div>
                  <label htmlFor="addressLine1" className="block text-sm font-medium text-gray-700">
                    Address Line 1
                  </label>
                  <input
                    type="text"
                    id="addressLine1"
                    name="addressLine1"
                    value={shippingInfo.addressLine1}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.addressLine1 && <p className="text-red-500 text-sm mt-1">{errors.addressLine1}</p>}
                </div>
                <div>
                  <label htmlFor="addressLine2" className="block text-sm font-medium text-gray-700">
                    Address Line 2 (Optional)
                  </label>
                  <input
                    type="text"
                    id="addressLine2"
                    name="addressLine2"
                    value={shippingInfo.addressLine2}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                  />
                </div>
                <div>
                  <label htmlFor="city" className="block text-sm font-medium text-gray-700">
                    City
                  </label>
                  <input
                    type="text"
                    id="city"
                    name="city"
                    value={shippingInfo.city}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.city && <p className="text-red-500 text-sm mt-1">{errors.city}</p>}
                </div>
                <div>
                  <label htmlFor="country" className="block text-sm font-medium text-gray-700">
                    Country
                  </label>
                  <input
                    type="text"
                    id="country"
                    name="country"
                    value={shippingInfo.country}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.country && <p className="text-red-500 text-sm mt-1">{errors.country}</p>}
                </div>
                <div>
                  <label htmlFor="state" className="block text-sm font-medium text-gray-700">
                    State/Province
                  </label>
                  <input
                    type="text"
                    id="state"
                    name="state"
                    value={shippingInfo.state}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.state && <p className="text-red-500 text-sm mt-1">{errors.state}</p>}
                </div>
                <div>
                  <label htmlFor="postalCode" className="block text-sm font-medium text-gray-700">
                    Postal Code
                  </label>
                  <input
                    type="text"
                    id="postalCode"
                    name="postalCode"
                    value={shippingInfo.postalCode}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.postalCode && <p className="text-red-500 text-sm mt-1">{errors.postalCode}</p>}
                </div>
                <div>
                  <label htmlFor="phone" className="block text-sm font-medium text-gray-700">
                    Phone Number
                  </label>
                  <input
                    type="tel"
                    id="phone"
                    name="phone"
                    value={shippingInfo.phone}
                    onChange={handleShippingInfoChange}
                    className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                    required
                  />
                  {errors.phone && <p className="text-red-500 text-sm mt-1">{errors.phone}</p>}
                </div>
              </div>
              <div className="mt-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Shipping Method
                </label>
                <div className="space-y-2">
                  <label className="flex items-center">
                    <input
                      type="radio"
                      name="shippingMethod"
                      value="standard"
                      checked={shippingMethod === "standard"}
                      onChange={(e) => setShippingMethod(e.target.value)}
                      className="form-radio h-4 w-4 text-indigo-600"
                    />
                    <span className="ml-2">Standard Shipping (5-7 business days)</span>
                  </label>
                  <label className="flex items-center">
                    <input
                      type="radio"
                      name="shippingMethod"
                      value="express"
                      checked={shippingMethod === "express"}
                      onChange={(e) => setShippingMethod(e.target.value)}
                      className="form-radio h-4 w-4 text-indigo-600"
                    />
                    <span className="ml-2">Express Shipping (2-3 business days)</span>
                  </label>
                </div>
              </div>
            </div>
            <div className="bg-white p-6 rounded-lg shadow-md">
              <h2 className="text-2xl font-semibold mb-4">Payment Details</h2>
              <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Payment Method
                </label>
                <div className="flex space-x-4">
                  <button
                    type="button"
                    onClick={() => setPaymentMethod("creditCard")}
                    className={`flex items-center justify-center px-4 py-2 border rounded-md ${paymentMethod === "creditCard" ? "border-indigo-500 text-indigo-500" : "border-gray-300 text-gray-700"}`}
                  >
                    <FaCreditCard className="mr-2" /> Credit Card
                  </button>
                  <button
                    type="button"
                    onClick={() => setPaymentMethod("paypal")}
                    className={`flex items-center justify-center px-4 py-2 border rounded-md ${paymentMethod === "paypal" ? "border-indigo-500 text-indigo-500" : "border-gray-300 text-gray-700"}`}
                  >
                    <FaPaypal className="mr-2" /> PayPal
                  </button>
                  <button
                    type="button"
                    onClick={() => setPaymentMethod("applePay")}
                    className={`flex items-center justify-center px-4 py-2 border rounded-md ${paymentMethod === "applePay" ? "border-indigo-500 text-indigo-500" : "border-gray-300 text-gray-700"}`}
                  >
                    <FaApplePay className="mr-2" /> Apple Pay
                  </button>
                </div>
              </div>
              {paymentMethod === "creditCard" && (
                <div className="space-y-4">
                  <div>
                    <label htmlFor="cardNumber" className="block text-sm font-medium text-gray-700">
                      Card Number
                    </label>
                    <input
                      type="text"
                      id="cardNumber"
                      name="cardNumber"
                      value={paymentInfo.cardNumber}
                      onChange={handlePaymentInfoChange}
                      className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                      required
                    />
                    {errors.cardNumber && <p className="text-red-500 text-sm mt-1">{errors.cardNumber}</p>}
                  </div>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <label htmlFor="expirationDate" className="block text-sm font-medium text-gray-700">
                        Expiration Date
                      </label>
                      <input
                        type="text"
                        id="expirationDate"
                        name="expirationDate"
                        value={paymentInfo.expirationDate}
                        onChange={handlePaymentInfoChange}
                        placeholder="MM/YY"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                        required
                      />
                      {errors.expirationDate && <p className="text-red-500 text-sm mt-1">{errors.expirationDate}</p>}
                    </div>
                    <div>
                      <label htmlFor="cvv" className="block text-sm font-medium text-gray-700">
                        CVV
                      </label>
                      <input
                        type="password"
                        id="cvv"
                        name="cvv"
                        value={paymentInfo.cvv}
                        onChange={handlePaymentInfoChange}
                        maxLength="4"
                        className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"
                        required
                      />
                      {errors.cvv && <p className="text-red-500 text-sm mt-1">{errors.cvv}</p>}
                    </div>
                  </div>
                </div>
              )}
              {paymentMethod === "paypal" && (
                <p className="text-gray-600">You will be redirected to PayPal to complete your payment.</p>
              )}
              {paymentMethod === "applePay" && (
                <p className="text-gray-600">You will be prompted to use Apple Pay to complete your payment.</p>
              )}
            </div>
            <div className="mt-8">
              <button
                type="submit"
                className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition duration-150 ease-in-out"
              >
                Place Order
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default OrderDetailsPage;