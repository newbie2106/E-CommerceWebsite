import React, { useState, useEffect, useContext } from 'react';
import { addToCart, clearCart, fetchCartItems, removeFromCart, updateCartQuantity } from '../configs/APIs';
import { MyDispatchContext, MyUserContext } from '../App';
import { FaMinus, FaPlus, FaTrash } from 'react-icons/fa';
import VNDCurrencyFormat from '../configs/Utils';
import { useNavigate } from 'react-router-dom';

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);
    const user = useContext(MyUserContext);
    const totalItems = cartItems.reduce((sum, item) => sum + item.quantity, 0);
    const totalPrice = cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
    const nav = useNavigate();
    const loadCartItems = async () => {
        const items = await fetchCartItems(user.username);
        setCartItems(items);
    };

    useEffect(() => {
        if (user.username) {
            loadCartItems();
        }
    }, [user.username]);

    // const handleAddToCart = async (productId, quantity) => {
    //     await addToCart(user.username, productId, quantity);
    //     const items = await fetchCartItems(user.username); // Cập nhật giỏ hàng sau khi thêm
    //     setCartItems(items);
    // };

    const handleBack = () => {
        nav("/");
    }
    const handlePayment = () => {
        nav("/pay");
    }

    const handleUpdateCartQuantity = async (productId, quantity) => {
        await updateCartQuantity(user.username, productId, quantity);
        const items = await fetchCartItems(user.username); // Cập nhật giỏ hàng sau khi thay đổi số lượng
        setCartItems(items);
    };

    const handleRemoveFromCart = async (productId) => {
        await removeFromCart(user.username, productId);
        const items = await fetchCartItems(user.username); // Cập nhật giỏ hàng sau khi xóa sản phẩm
        setCartItems(items);
    };

    const handleClearCart = async () => {
        await clearCart(user.username);
        setCartItems([]); // Làm rỗng giỏ hàng sau khi xóa tất cả sản phẩm
    };

    return (
        <div className="container mx-auto p-4 max-w-3xl">
            <h1 className="text-3xl font-bold mb-6">Shopping Cart</h1>
            <div className="text-right mb-4">
                <button
                    onClick={handleClearCart}
                    className="text-red-500 hover:text-red-700 transition-colors duration-200 font-semibold"
                    aria-label="Clear cart"
                >
                    Xóa tất cả
                </button>
            </div>
            <div className="space-y-4">
                {cartItems.map(item => (
                    <div
                        key={item.productId}
                        className="flex items-center justify-between bg-white p-4 rounded-lg shadow-md transition-all duration-300 hover:shadow-lg"
                    >
                        <div className="flex items-center space-x-4">
                            <img
                                src={item.image} // Thay thế bằng thuộc tính hình ảnh
                                alt={item.productName} // Sửa lại tên sản phẩm
                                className="w-20 h-20 object-cover rounded"
                            />
                            <div>
                                <h2 className="text-xl font-semibold">{item.productName}</h2>
                                <p className="text-gray-600">{VNDCurrencyFormat.format(item.price)}</p>
                            </div>
                        </div>
                        <div className="flex items-center space-x-4">
                            <div className="flex items-center space-x-2">
                                <button
                                    onClick={() => handleUpdateCartQuantity(item.productId, item.quantity - 1)} // Giảm số lượng
                                    className="bg-gray-200 p-1 rounded-full hover:bg-gray-300 transition-colors duration-200"
                                    aria-label="Decrease quantity"
                                >
                                    <FaMinus className="text-gray-600" />
                                </button>
                                <span className="font-semibold">{item.quantity}</span>
                                <button
                                    onClick={() => handleUpdateCartQuantity(item.productId, item.quantity + 1)} // Tăng số lượng
                                    className="bg-gray-200 p-1 rounded-full hover:bg-gray-300 transition-colors duration-200"
                                    aria-label="Increase quantity"
                                >
                                    <FaPlus className="text-gray-600" />
                                </button>
                            </div>
                            <button
                                onClick={() => handleRemoveFromCart(item.productId)} // Xóa sản phẩm
                                className="text-red-500 hover:text-red-700 transition-colors duration-200"
                                aria-label="Remove item"
                            >
                                <FaTrash />
                            </button>
                        </div>
                    </div>
                ))}
            </div>
            <div className="mt-8 bg-gray-100 p-4 rounded-lg">
                <h2 className="text-2xl font-bold">Total Items: {totalItems}</h2>
                <h2 className="text-2xl font-bold">Total Price: {VNDCurrencyFormat.format(totalPrice)}</h2>
            </div>

            {/* Thêm hai nút "Trở về" và "Thanh toán" */}
            <div className="flex justify-between mt-6">
                <button onClick={handleBack} className="bg-white text-red-500 border border-red-500 hover:bg-red-500 hover:text-white transition duration-200 px-4 py-2 rounded">
                    Trở về
                </button>
                <button onClick={handlePayment} className="bg-red-500 text-white hover:bg-white hover:text-red-500 transition hover:border hover:border-red-500 duration-200 px-4 py-2 rounded">
                    Thanh toán
                </button>
            </div>
        </div>
    );
};

export default Cart;
