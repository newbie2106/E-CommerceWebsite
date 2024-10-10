import React, { useContext, useEffect, useState } from "react";
import { FaChevronDown, FaChevronUp, FaTimes, FaCreditCard, FaPaypal, FaApplePay, FaMapMarkerAlt } from "react-icons/fa";
import { createSaleOrder, fetchCartItems, getAllShippingAddresses, loadCarrier, loadDistrictsByProvinceCode, loadProvinces, loadWardsByDistrictCode, paymentVNPay } from "../configs/APIs";
import { MyUserContext } from "../App";
import VNDCurrencyFormat from "../configs/Utils";
import { Link, useNavigate } from "react-router-dom";
import { BsCashCoin } from "react-icons/bs";

const OrderDetailsPage = () => {
  const [items, setItems] = useState([]);
  const user = useContext(MyUserContext);
  const [carrier, setCarrier] = useState([]);
  const [note, setNote] = useState("");
  const [addresses, setAddresses] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedAddressId, setSelectedAddressId] = useState(null);
  const [isPaid, setIsPaid] = useState(false);
  const nav = useNavigate();

  useEffect(() => {
    const fetchAddresses = async () => {

      try {
        const res = await getAllShippingAddresses(user.username);
        const newAddresses = res.data;
        setAddresses(newAddresses);

        const defaultAddress = newAddresses.find((address) => address.isDefault);
        setSelectedAddressId(defaultAddress?.id || null);
      } catch (err) {
        console.error('Error fetching addresses:', err);
      }
    };

    fetchAddresses();
  }, [user.username]);
  const loadChooseCarrier = async () => {
    const carrier = await loadCarrier();
    setCarrier(carrier);
  };

  useEffect(() => {
    loadChooseCarrier();
  }, []);

  const loadCartItems = async () => {
    const items = await fetchCartItems(user.username);
    setItems(items);
  };

  useEffect(() => {
    if (user.username) {
      loadCartItems();
    }
  }, [user.username]);
  const [shippingMethod, setShippingMethod] = useState("");
  const [paymentInfo, setPaymentInfo] = useState({
    cardNumber: "",
    expirationDate: "",
    cvv: "",
  });
  const [paymentMethod, setPaymentMethod] = useState("cash");
  const [expandedItems, setExpandedItems] = useState([]);

  const toggleItemExpansion = (id) => {
    setExpandedItems((prev) =>
      prev.includes(id) ? prev.filter((itemId) => itemId !== id) : [...prev, id]
    );
  };

  const removeItem = (id) => {
    setItems((prev) => prev.filter((item) => item.id !== id));
  };

  // const handleShippingInfoChange = (e) => {
  //   const { name, value } = e.target;
  //   setShippingInfo((prev) => ({ ...prev, [name]: value }));
  // };

  // const handlePaymentInfoChange = (e) => {
  //   const { name, value } = e.target;
  //   setPaymentInfo((prev) => ({ ...prev, [name]: value }));
  // };

  const carrierId = parseInt(shippingMethod, 10) || null;

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Xử lý thanh toán, gửi dữ liệu đến API
    const saleOrderRequest = {
      username: user.username,
      totalAmount: totalCost,
      isPaid: isPaid,
      note,
      branchId: 1,
      shippingAdressId: selectedAddressId,
      carrierId: carrierId,
      orderDetails: items
    };
    if (isPaid) {
      localStorage.setItem("username", user.username);
      localStorage.setItem("selectedAddressId", selectedAddressId);
      localStorage.setItem("carrierId", carrierId);
      localStorage.setItem("items", JSON.stringify(items));
      localStorage.setItem("note", note);
      const response = await paymentVNPay(totalCost);
      window.location.href = response.data.paymentUrl
    } else {
      try {
        const response = await createSaleOrder(saleOrderRequest);
        alert("Đơn hàng đã được tạo thành công: " + response);
        nav("/");
      } catch (error) {
        alert("Đã có lỗi xảy ra khi tạo đơn hàng: " + error.message);
      }
    }
    console.log('Đơn hàng:', saleOrderRequest);
  };

  // Cập nhật địa chỉ mặc định
  const handleAddressChange = (addressId) => {
    setSelectedAddressId(addressId);
  };

  const handleCancelChange = (e) => {
    e.preventDefault();
    setShowModal(false);
  };

  // Xác nhận thay đổi địa chỉ mặc định
  const handleConfirmChange = (e) => {
    e.preventDefault();
    // Cập nhật địa chỉ default với địa chỉ đã chọn
    setAddresses((prevAddresses) =>
      prevAddresses.map((address) =>
        address.id === selectedAddressId
          ? { ...address, isDefault: true }
          : { ...address, isDefault: false }
      )
    );
    setShowModal(false);
  };

  const totalCost = items.reduce((sum, item) => sum + item.quantity * item.price, 0);

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-8">Chi tiết đơn hàng</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-2xl font-semibold mb-4">Tóm tắt đơn hàng</h2>
          {items.map((item) => (
            <div key={item.id} className="mb-4 border-b pb-4">
              <div className="flex items-center justify-between">
                <div className="flex items-center">
                  <img src={item.image} alt={item.name} className="w-16 h-16 object-cover rounded mr-4" />
                  <div>
                    <h3 className="font-semibold">{item.name}</h3>
                    <p className="text-gray-600">
                      {item.quantity} x {VNDCurrencyFormat.format(item.price)}
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
          <div>
            <label className="block mb-1">Ghi Chú:</label>
            <textarea
              value={note}
              onChange={(e) => setNote(e.target.value)}
              className="w-full p-2 border rounded"
            />
          </div>
          <div className="mt-6 text-xl font-semibold">
            Total: {VNDCurrencyFormat.format(totalCost)}
          </div>
        </div>
        <div>
          <form onSubmit={handleSubmit}>
            <div className="bg-white p-6 rounded-lg shadow-md mb-8">
              <h2 className="text-2xl font-semibold mb-4">Thông tin đặt hàng</h2>
              <div className="w-full">
                {/* Hiển thị địa chỉ mặc định */}
                {addresses
                  .filter((address) => address.id === selectedAddressId)
                  .map((address) => (
                    <div
                      key={address.id}
                      className="p-4 bg-gray-100 rounded-lg shadow-md w-full flex justify-between items-start"
                    >
                      <div>
                        <h2 className="text-lg font-bold text-gray-800">
                          <FaMapMarkerAlt className="inline-block text-blue-500 mr-2" />
                          {address.fullName}
                        </h2>
                        <p className="text-gray-600">
                          <span className="font-bold">Địa chỉ: </span>
                          {address.address}, {address.wardCode.fullName},{" "}
                          {address.districtCode.fullName}, {address.provinceCode.fullName}
                        </p>
                        <p className="text-gray-600">
                          <span className="font-bold">Số điện thoại: </span> {address.phone}
                        </p>
                      </div>

                      {/* Nút thay đổi */}
                      <button
                        onClick={() => setShowModal(true)}
                        className="text-blue-500 font-semibold hover:underline"
                      >
                        Thay đổi
                      </button>
                    </div>
                  ))}

                {/* Modal thay đổi địa chỉ */}
                {showModal && (
                  <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex items-center justify-center z-50">
                    <div className="bg-white p-8 rounded-lg shadow-2xl max-w-xl w-full relative">
                      <h2 className="text-2xl font-semibold mb-6 text-gray-800 text-center">Chọn địa chỉ mới</h2>

                      <div className="space-y-6 max-h-80 overflow-y-auto scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100">
                        {addresses.map((address) => (
                          <div
                            key={address.id}
                            className={`p-4 rounded-lg shadow-md flex items-start border ${selectedAddressId === address.id ? 'border-blue-500' : 'border-gray-300'}`}
                          >
                            <input
                              type="radio"
                              id={`address-${address.id}`}
                              name="address"
                              value={address.id}
                              checked={selectedAddressId === address.id}
                              onChange={() => handleAddressChange(address.id)}
                              className="mr-4 mt-1 accent-blue-500"
                            />
                            <label htmlFor={`address-${address.id}`} className="text-gray-700 w-full">
                              <p className="font-bold text-lg">{address.fullName}</p>
                              <p className="text-sm text-gray-600">{address.address}, {address.wardCode.fullName}, {address.districtCode.fullName}, {address.provinceCode.fullName}</p>
                              <p className="text-sm text-gray-600"><strong>Số điện thoại:</strong> {address.phone}</p>
                            </label>
                          </div>
                        ))}
                      </div>

                      <div className="mt-6 flex justify-between">
                        <button
                          type="button"
                          onClick={handleCancelChange}
                          className="bg-gray-300 text-gray-700 hover:bg-gray-400 px-4 py-2 rounded-md transition duration-200"
                        >
                          Hủy bỏ
                        </button>
                        <button
                          type="button"
                          onClick={handleConfirmChange}
                          className="bg-blue-500 text-white hover:bg-blue-600 px-4 py-2 rounded-md transition duration-200"
                        >
                          Xác nhận
                        </button>
                      </div>
                    </div>
                  </div>
                )}

              </div>

              <div className="mt-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Phương thức vận chuyển
                </label>
                {carrier.map((c) => (
                  <div value={c.id} className="space-y-2">
                    <label className="flex items-center">
                      <input
                        type="radio"
                        name="shippingMethod"
                        value={c.id}
                        checked={shippingMethod === `${c.id}`}
                        onChange={(e) => setShippingMethod(e.target.value)}
                        className="form-radio h-4 w-4 text-indigo-600" required
                      />
                      <span className="ml-2 font-bold">{c.name} <span className="text-gray-600 text-sm">{c.description}</span></span>
                    </label>
                  </div>
                ))}

              </div>
            </div>
            <div className="bg-white p-6 rounded-lg shadow-md">
              <h2 className="text-2xl font-semibold mb-4">Phương thức thanh toán</h2>
              <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Payment Method
                </label>
                <div className="flex space-x-4">
                  <button
                    type="button"
                    onClick={() => {
                      setPaymentMethod("cash")
                      setIsPaid(false);
                    }}
                    className={`flex items-center justify-center px-4 py-2 border rounded-md ${paymentMethod === "cash" ? "border-indigo-500 text-indigo-500" : "border-gray-300 text-gray-700"}`}
                  >
                    <BsCashCoin className="mr-2" /> Tiền mặt
                  </button>
                  <button
                    type="button"
                    onClick={() => {
                      setPaymentMethod("creditCard")
                      setIsPaid(true)
                    }}
                    className={`flex items-center justify-center px-4 py-2 border rounded-md ${paymentMethod === "creditCard" ? "border-indigo-500 text-indigo-500" : "border-gray-300 text-gray-700"}`}
                  >
                    <FaCreditCard className="mr-2" /> Credit Card
                  </button>
                </div>
              </div>
              {paymentMethod === "cash" && (
                <p className="text-gray-600">Thanh toán sau khi nhận hàng</p>
              )}
            </div>
            <div className="mt-8">
              <button
                type="submit"
                className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition duration-150 ease-in-out"
              >
                Thanh toán
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default OrderDetailsPage;