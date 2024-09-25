import React, { useContext, useEffect, useRef, useState } from "react";
import { FaStar, FaShoppingCart, FaChevronLeft, FaChevronRight } from "react-icons/fa";
import { addToCart, fetchCartItems, productDetail } from "../configs/APIs";
import { useNavigate, useParams } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../App";
import LoadingAnimations from './LoadingAnimations'; // Đảm bảo import LoadingAnimations
import AddToCartNotification from "./AddToCartNotification";

const ViewProduct = () => {
    const [loading, setLoading] = useState(true);
    const [quantity, setQuantity] = useState(1);
    const [activeImage, setActiveImage] = useState(0);
    const nav = useNavigate();
    const [product, setProduct] = useState(null);
    const { productId } = useParams();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const proccessComment = useRef(null);
    const [comment, setComment] = useState("");
    const [listComment, setListComment] = useState([]);
    const [showNotification, setShowNotification] = useState(false);

    // Modal state
    const [showModal, setShowModal] = useState(false);
    const [countdown, setCountdown] = useState(10);

    useEffect(() => {
        setLoading(true); // Bắt đầu loading
        productDetail(productId)
            .then((res) => {
                if (res.status === 200) {
                    setProduct(res.data);
                    console.log(res.data);
                } else {
                    console.log("ERROR");
                }
            })
            .finally(() => setLoading(false)); // Tắt loading khi đã nhận được dữ liệu
    }, [productId]);

    const handleAddToCart = async (productId, quantity) => {
        if (user) {
            await addToCart(user.username, productId, quantity);
            fetchCartItems(user.username);
            setShowNotification(true);
        } else {
            setShowModal(true); // Hiển thị modal nếu chưa đăng nhập
        }
    };

    useEffect(() => {
        let timer;
        if (showModal && countdown > 0) {
            timer = setInterval(() => {
                setCountdown((prev) => prev - 1);
            }, 1000);
        } else if (countdown === 0) {
            setShowModal(false);
            setCountdown(10); // Reset lại countdown
        }
        return () => clearInterval(timer);
    }, [showModal, countdown]);

    const handleLoginClick = () => {
        nav("/login");
    };

    const handleImageChange = (index) => {
        setActiveImage(index);
    };

    const handlePrevImage = () => {
        setActiveImage((prev) => (prev === 0 ? product.imageUrls.length - 1 : prev - 1));
    };

    const handleNextImage = () => {
        setActiveImage((prev) => (prev === product.imageUrls.length - 1 ? 0 : prev + 1));
    };

    const handleGoBack = () => {
        setShowNotification(false); // Đóng thông báo
    };

    const handleViewCart = () => {
        nav("/cart"); // Chuyển hướng đến giỏ hàng
    };

    if (loading) {
        return <LoadingAnimations />; // Hiển thị loading trong khi tải sản phẩm
    }

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="flex flex-col md:flex-row">
                <div className="md:w-1/2 mb-8 md:mb-0">
                    <div className="relative">
                        <img
                            src={product.imageUrls[activeImage]}
                            alt={product.name}
                            className="w-[70%] h-auto object-cover rounded-lg shadow-lg mx-auto"
                        />
                        <button
                            onClick={handlePrevImage}
                            className="absolute left-2 top-1/2 transform -translate-y-1/2 bg-white bg-opacity-50 rounded-full p-2 hover:bg-opacity-75 transition-all"
                            aria-label="Previous image"
                        >
                            <FaChevronLeft className="text-gray-800" />
                        </button>
                        <button
                            onClick={handleNextImage}
                            className="absolute right-2 top-1/2 transform -translate-y-1/2 bg-white bg-opacity-50 rounded-full p-2 hover:bg-opacity-75 transition-all"
                            aria-label="Next image"
                        >
                            <FaChevronRight className="text-gray-800" />
                        </button>
                    </div>
                    <div className="flex mt-4 space-x-2 overflow-x-auto">
                        {product.imageUrls.map((image, index) => (
                            <img
                                key={index}
                                src={image}
                                alt={`${product.name} thumbnail ${index + 1}`}
                                className={`w-14 h-14 object-cover rounded-md cursor-pointer ${index === activeImage ? "ring-2 ring-blue-500" : ""}`}
                                onClick={() => handleImageChange(index)}
                            />
                        ))}
                    </div>
                </div>
                <div className="md:w-1/2 md:pl-8">
                    <h1 className="text-3xl font-bold mb-4">{product.name}</h1>
                    <p className="text-2xl font-semibold text-blue-600 mb-4">{product.price.toLocaleString('vi-VN')} VND</p>
                    <p className="text-gray-600 mb-6">{product.description}</p>

                    <div className="mb-6">
                        <h2 className="text-lg font-semibold mb-2">Quantity:</h2>
                        <div className="flex items-center space-x-2">
                            <button
                                className="px-3 py-1 border rounded-md"
                                onClick={() => setQuantity((prev) => Math.max(1, prev - 1))}
                            >
                                -
                            </button>
                            <span className="text-xl font-semibold">{quantity}</span>
                            <button
                                className="px-3 py-1 border rounded-md"
                                onClick={() => setQuantity((prev) => prev + 1)}
                            >
                                +
                            </button>
                        </div>
                    </div>
                    <button
                        className="w-full bg-blue-600 text-white py-3 rounded-md font-semibold hover:bg-blue-700 transition-colors flex items-center justify-center space-x-2"
                        onClick={() => handleAddToCart(product.id, quantity)}
                    >
                        <FaShoppingCart />
                        <span>Add to Cart</span>
                    </button>
                </div>
                {showNotification && (
                    <AddToCartNotification
                        onGoBack={handleGoBack}
                        onViewCart={handleViewCart}
                    />
                )}
            </div>

            {/* Modal thông báo */}
            {showModal && (
                <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
                    <div className="bg-white p-6 rounded-lg shadow-lg text-center relative">
                        <button
                            className="absolute top-0 right-0 m-0 p-1 hover:bg-red-500 rounded-bl"
                            onClick={() => {
                                setShowModal(false);
                                setCountdown(10); // Reset lại countdown
                            }}
                            aria-label="Close"
                        >
                            X
                        </button>
                        <p className="mb-4">Bạn chưa đăng nhập! Vui lòng đăng nhập để thêm sản phẩm vào giỏ.</p>
                        <button className="bg-blue-500 text-white py-2 px-4 rounded" onClick={handleLoginClick}>
                            Đăng nhập
                        </button>
                        <div className="mt-4">
                            <p className="mb-2">Đóng lại sau: {countdown} giây</p>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ViewProduct;

