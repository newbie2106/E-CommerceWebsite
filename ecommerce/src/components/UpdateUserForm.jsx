import React, { useContext, useEffect, useState } from "react";
import { FaUser, FaLock, FaEnvelope, FaPhone, FaMapMarkerAlt } from "react-icons/fa";
import { getCurrentUser, loadDistrictsByProvinceCode, loadProvinces, loadWardsByDistrictCode, updateInfoUser } from "../configs/APIs"; // Adjust path to your API updates
import { useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import LoadingAnimations from "./LoadingAnimations";

const UpdateUserForm = () => {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [loadingUser, setLoadingUser] = useState(true);
    const [provinces, setProvinces] = useState([]);
    const [districts, setDistricts] = useState([]);
    const [wards, setWards] = useState([]);
    const [selectedProvince, setSelectedProvince] = useState('');
    const [selectedDistrict, setSelectedDistrict] = useState('');
    const [selectedWard, setSelectedWard] = useState('');
    const [avatar, setAvatar] = useState(null);
    const nav = useNavigate();
    const [user, setUser] = useState({});

    useEffect(() => {
        const fetchCurrentUser = async () => {
            try {
                const res = await getCurrentUser();
                if (res && res.data) {
                    setUser(res.data);
                }
            } catch (error) {
                console.error("Failed to fetch user data:", error);
            } finally {
                setLoadingUser(false);
            }
        };
        fetchCurrentUser();
    }, []);

    useEffect(() => {
        const fetchProvinces = async () => {
            const result = await loadProvinces();
            setProvinces(result);
        };
        fetchProvinces();
    }, []);

    useEffect(() => {
        if (selectedProvince) {
            const fetchDistricts = async () => {
                const result = await loadDistrictsByProvinceCode(selectedProvince);
                setDistricts(result);
                setWards([]); // Clear wards when province changes
            };
            fetchDistricts();
        }
    }, [selectedProvince]);

    useEffect(() => {
        if (selectedDistrict) {
            const fetchWards = async () => {
                const result = await loadWardsByDistrictCode(selectedDistrict);
                setWards(result);
            };
            fetchWards();
        }
    }, [selectedDistrict]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);

        const formData = new FormData(e.target);
        if (avatar) {
            formData.append("avatar", avatar);
        }
        for (let key in user) {
            formData.append(key, user[key]);
            console.log("1" + key)
        }

        try {
            const response = await updateInfoUser(user.username, formData);
            if (response.status === 200) {
                alert('Update successful');
                nav('/profile'); // Navigate to profile after success
            } else {
                alert('Update failed');
            }
        } catch (err) {
            alert('An error occurred during update');
        }
        setIsSubmitting(false);
    };

    const handleAvatarChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setAvatar(file);
        }
    };

    if (loadingUser) {
        return <LoadingAnimations />;
    }

    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-100 to-purple-100 flex items-center justify-center p-4">
            <div className="bg-white rounded-lg shadow-xl p-8 w-full max-w-md">
                <h2 className="text-3xl font-bold text-center mb-8 text-gray-800">Update User Info</h2>
                <form onSubmit={handleSubmit} className="space-y-6" encType="multipart/form-data">

                    {/* Username as read-only text */}
                    <div className="relative mb-4">
                        <FaUser className="absolute top-3 left-3 text-gray-400" />
                        <input
                            type="text"
                            name="username"
                            value={user.username}
                            readOnly
                            className="w-full pl-10 pr-3 py-2 rounded-lg border border-gray-300 bg-gray-200 cursor-not-allowed"
                            placeholder="Username"
                        />
                    </div>

                    {/* Password field, only allow new password input */}
                    <div className="relative mb-4">
                        <input
                            type="password"
                            name="password"
                            value={user.password}
                            className="w-full pl-10 pr-3 py-2 rounded-lg border border-gray-300 hidden"
                            placeholder="New Password (leave empty if not changing)"
                        />
                    </div>

                    {/* Other user info fields */}
                    <div className="flex space-x-4">
                        <div className="flex-1">
                            <input
                                type="text"
                                name="firstName"
                                defaultValue={user.firstName}
                                required
                                className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                                placeholder="First Name"
                            />
                        </div>
                        <div className="flex-1">
                            <input
                                type="text"
                                name="lastName"
                                defaultValue={user.lastName}
                                required
                                className="w-full px-3 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                                placeholder="Last Name"
                            />
                        </div>
                    </div>

                    <div className="relative mb-4">
                        <FaMapMarkerAlt className="absolute top-3 left-3 text-gray-400" />
                        <input
                            type="text"
                            name="address"
                            defaultValue={user.customer.address}
                            required
                            className="w-full pl-10 pr-3 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Address"
                        />
                    </div>

                    <div className="relative mb-4">
                        <FaPhone className="absolute top-3 left-3 text-gray-400" />
                        <input
                            type="tel"
                            name="phone"
                            defaultValue={user.customer.phone}
                            required
                            pattern="^\d+$"
                            className="w-full pl-10 pr-3 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Phone Number"
                        />
                    </div>

                    <div className="relative mb-4">
                        <FaEnvelope className="absolute top-3 left-3 text-gray-400" />
                        <input
                            type="email"
                            name="email"
                            defaultValue={user.customer.email}
                            required
                            className="w-full pl-10 pr-3 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Email"
                        />
                    </div>

                    {/* Province Dropdown */}
                    <div className="mb-4">
                        <label htmlFor="province" className="block text-sm font-medium text-gray-700 mb-2">Province</label>
                        <select
                            id="province"
                            name="provinceCode"
                            value={selectedProvince}
                            onChange={(e) => setSelectedProvince(e.target.value)}
                            className="block w-full pl-3 pr-10 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                            required
                        >
                            <option value="">Select Province</option>
                            {provinces.map(province => (
                                <option key={province.code} value={province.code}>
                                    {province.fullName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* District Dropdown */}
                    <div className="mb-4">
                        <label htmlFor="district" className="block text-sm font-medium text-gray-700 mb-2">District</label>
                        <select
                            id="district"
                            name="districtCode"
                            defaultValue={user.customer.districtId?.code}
                            value={selectedDistrict}
                            onChange={(e) => setSelectedDistrict(e.target.value)}
                            className="block w-full pl-3 pr-10 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                            required
                        >
                            <option value="">Select District</option>
                            {districts.map(district => (
                                <option key={district.code} value={district.code}>
                                    {district.fullName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Ward Dropdown */}
                    <div className="mb-4">
                        <label htmlFor="ward" className="block text-sm font-medium text-gray-700 mb-2">Ward</label>
                        <select
                            id="ward"
                            name="wardCode"
                            defaultValue={user.customer.wardId?.code}
                            value={selectedWard}
                            onChange={(e) => setSelectedWard(e.target.value)}
                            className="block w-full pl-3 pr-10 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                            required
                        >
                            <option value="">Select Ward</option>
                            {wards.map(ward => (
                                <option key={ward.code} value={ward.code}>
                                    {ward.fullName}
                                </option>
                            ))}
                        </select>
                    </div>

                    {/* Avatar Upload */}
                    <div className="mb-4">
                        <label htmlFor="avatar" className="block text-sm font-medium text-gray-700 mb-2">Avatar</label>
                        <input
                            type="file"
                            name="avatar"
                            accept="image/*"
                            onChange={handleAvatarChange}
                            className="block w-full text-gray-500 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                        />
                    </div>

                    <button
                        type="submit"
                        className={`w-full py-2 px-4 rounded-lg bg-blue-600 text-white font-semibold ${isSubmitting ? "opacity-50 cursor-not-allowed" : ""}`}
                        disabled={isSubmitting}
                    >
                        {isSubmitting ? "Updating..." : "Update Info"}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default UpdateUserForm;
