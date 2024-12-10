import React, { useContext, useEffect, useRef, useState } from "react";
import { FaStar, FaShoppingCart, FaChevronLeft, FaChevronRight } from "react-icons/fa";
import { addComment, addToCart, fetchCartItems, getCommentByProductId, productDetail } from "../configs/APIs";
import { useNavigate, useParams } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../App";
import LoadingAnimations from './LoadingAnimations';
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
    const [commentSuccess, setCommentSuccess] = useState(false);

    const [showModal, setShowModal] = useState(false);
    const [countdown, setCountdown] = useState(10);

    useEffect(() => {
        setLoading(true); // Bắt đầu loading
        productDetail(productId)
            .then((res) => {
                if (res.status === 200) {
                    setProduct(res.data);
                } else {
                    console.log("ERROR");
                }
            })
            .finally(() => setLoading(false)); // Tắt loading khi đã nhận được dữ liệu
    }, [productId]);

    useEffect(() => {
        setLoading(true);
        getCommentByProductId(productId)
            .then((res) => {
                setListComment(res);
            }).finally(() => setLoading(false));

    }, [productId]);

    useEffect(() => {
        const loadImages = async () => {
            if (product && product.imageUrls) {
                const imagePromises = product.imageUrls.map((url) => {
                    return new Promise((resolve) => {
                        const img = new Image();
                        img.src = url;
                        img.onload = resolve;
                        img.onerror = () => resolve(); // Resolve ngay cả khi có lỗi
                    });
                });
                await Promise.all(imagePromises);
            }
        };

        loadImages().then(() => {
            setLoading(false); // Chỉ tắt loading khi tất cả hình ảnh đã được tải
        });
    }, [product]); // Theo dõi sự thay đổi của product

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

    if (loading || !product) {
        return <LoadingAnimations />; // Hiển thị loading trong khi tải sản phẩm
    }

    const calculateTimeDifference = (timestamp) => {
        const now = new Date();
        const createdAt = timestamp.toDate ? timestamp.toDate() : new Date(timestamp);
        const diffInMs = now - createdAt;
        const diffInMinutes = Math.floor(diffInMs / (1000 * 60));
        const diffInHours = Math.floor(diffInMs / (1000 * 60 * 60));
        const diffInDays = Math.floor(diffInMs / (1000 * 60 * 60 * 24));

        if (diffInDays > 0) {
            return `${diffInDays} ngày trước`;
        } else if (diffInHours > 0) {
            return `${diffInHours} giờ trước`;
        } else if (diffInMinutes > 0) {
            return `${diffInMinutes} phút trước`;
        } else {
            return 'Vừa xong';
        }
    };

    const handleCommentSubmit = async () => {
        if (!user) {
            alert("Vui lòng đăng nhập để bình luận");
            return;
        }

        const newComment = {
            content: comment,
            productId: productId,
            username: user.username
        };

        try {
            const res = await addComment(newComment);
            if (res.status === 201) {
                setComment(""); // Xóa nội dung sau khi bình luận
                setCommentSuccess(true); // Hiển thị thông báo thành công
                setTimeout(() => setCommentSuccess(false), 3000); // Tự động ẩn thông báo sau 3 giây
                const updatedComments = await getCommentByProductId(productId);
                setListComment(updatedComments); // Cập nhật danh sách bình luận
            }
        } catch (error) {
            console.error("Error adding comment:", error);
        }
    };

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
                                onClick={() => setQuantity((prev) => (prev > 1 ? prev - 1 : prev))}
                                className="border border-gray-300 rounded-md p-2"
                            >
                                -
                            </button>
                            <span className="text-lg">{quantity}</span>
                            <button
                                onClick={() => setQuantity((prev) => prev + 1)}
                                className="border border-gray-300 rounded-md p-2"
                            >
                                +
                            </button>
                        </div>
                    </div>

                    <button
                        onClick={() => handleAddToCart(product.id, quantity)}
                        className="bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-all"
                    >
                        <FaShoppingCart className="mr-2" /> Add to Cart
                    </button>

                    {showNotification && (
                        <AddToCartNotification onClose={() => setShowNotification(false)} />
                    )}
                    {showModal && (
                        <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 flex items-center justify-center">
                            <div className="bg-white rounded-lg p-8 text-center">
                                <h2 className="text-lg font-semibold mb-4">Please log in to add items to the cart</h2>
                                <button
                                    onClick={handleLoginClick}
                                    className="bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-all"
                                >
                                    Log in
                                </button>
                                <button
                                    onClick={() => setShowModal(false)}
                                    className="mt-4 bg-gray-300 text-gray-800 py-2 px-4 rounded-md hover:bg-gray-400 transition-all"
                                >
                                    Close
                                </button>
                            </div>
                        </div>
                    )}
                </div>
            </div>
            <div className="mt-8">
                <h2 className="text-2xl font-semibold mb-4">Comments</h2>
                {listComment.map((item, index) => (
                    <div key={index} className="border-b border-gray-300 mb-4 pb-4">
                        <p className="font-semibold">{item.username} <span className="text-gray-500 text-sm">{calculateTimeDifference(item.createdAt)}</span></p>
                        <p>{item.content}</p>
                    </div>
                ))}
                <div className="mt-4">
                    <textarea
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        className="w-full p-2 border border-gray-300 rounded-md"
                        rows="4"
                        placeholder="Add a comment..."
                    ></textarea>
                    <button
                        onClick={handleCommentSubmit}
                        className="mt-2 bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-all"
                    >
                        Submit
                    </button>
                    {commentSuccess && <p className="text-green-500 mt-2">Comment added successfully!</p>}
                </div>
            </div>
        </div>
    );
};

export default ViewProduct;
