import React, { useState, useEffect } from "react";
import { FaUser, FaEnvelope, FaPhone, FaMapMarkerAlt } from "react-icons/fa";
import { Link } from "react-router-dom";
import { getCurrentUser } from "../configs/APIs";
import LoadingAnimations from "./LoadingAnimations";

const Profile = () => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [isEditing, setIsEditing] = useState(false); // Để quản lý trạng thái chỉnh sửa
    const [errors, setErrors] = useState({}); // Để quản lý lỗi input

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await getCurrentUser();
                if (response && response.data) {
                    setUser(response.data);
                }
            } catch (error) {
                console.error("Error fetching user data:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    };

    if (loading) {
        return <LoadingAnimations />;
    }

    if (!user) {
        return <div className="text-center text-red-500">Không tìm thấy dữ liệu người dùng.</div>;
    }

    return (
        <div className="max-w-md mx-auto mt-10 bg-white shadow-md rounded-lg overflow-hidden">
            <div className="px-6 py-4">
                <h2 className="text-2xl font-bold text-center text-gray-800 mb-4">Hồ Sơ Của Tôi</h2>
                <div className="mb-4 text-center">
                    {user.avatar ? (
                        <img src={user.avatar} alt="User Avatar" className="w-32 h-32 bg-gray-300 rounded-full mx-auto object-cover" />
                    ) : (
                        <div className="w-32 h-32 bg-gray-300 rounded-full mx-auto flex items-center justify-center">
                            <span className="text-gray-600">Không có avatar.</span>
                        </div>
                    )}
                </div>
                <form>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                            Tên Đăng Nhập
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline bg-gray-100"
                            id="username"
                            type="text"
                            value={user.username}
                            readOnly
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="firstName">
                            Tên
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="firstName"
                            name="firstName"
                            type="text"
                            value={user.firstName}
                            onChange={handleInputChange}
                        />
                        {errors.firstName && <p className="text-red-500 text-xs italic">{errors.firstName}</p>}
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="lastName">
                            Họ
                        </label>
                        <input
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            id="lastName"
                            name="lastName"
                            type="text"
                            value={user.lastName}
                            onChange={handleInputChange}
                        />
                        {errors.lastName && <p className="text-red-500 text-xs italic">{errors.lastName}</p>}
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="email">
                            Email
                        </label>
                        <div className="relative">
                            <input
                                className="shadow appearance-none border rounded w-full py-2 pl-10 pr-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                id="email"
                                name="email"
                                type="email"
                                value={user.customer.email}
                                onChange={handleInputChange}
                            />
                            <FaEnvelope className="absolute left-3 top-3 text-gray-400" />
                        </div>
                        {errors.email && <p className="text-red-500 text-xs italic">{errors.email}</p>}
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="phone">
                            Điện Thoại
                        </label>
                        <div className="relative">
                            <input
                                className="shadow appearance-none border rounded w-full py-2 pl-10 pr-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                id="phone"
                                name="phone"
                                type="tel"
                                value={user.customer.phone}
                                onChange={handleInputChange}
                            />
                            <FaPhone className="absolute left-3 top-3 text-gray-400" />
                        </div>
                        {errors.phone && <p className="text-red-500 text-xs italic">{errors.phone}</p>}
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="address">
                            Địa Chỉ
                        </label>
                        <div className="relative">
                            <input
                                className="shadow appearance-none border rounded w-full py-2 pl-10 pr-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                id="address"
                                name="address"
                                type="text"
                                value={user.customer.address}
                                onChange={handleInputChange}
                            />
                            <FaMapMarkerAlt className="absolute left-3 top-3 text-gray-400" />
                        </div>
                    </div>
                    <div className="mb-6">

                        <div className="relative">
                            <Link
                                to="/edit-profile"
                                className="bg-green-500 hover:bg-gray-300 text-white font-bold py-2 px-4 rounded focus:outline-none focus:ring transition duration-300"
                            >
                                Chỉnh sửa thông tin
                            </Link>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Profile;
