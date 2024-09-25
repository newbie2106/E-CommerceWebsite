import React, { useEffect, useRef, useState } from "react";
import { useForm } from "react-hook-form";
import { FaUser, FaLock, FaEnvelope, FaPhone, FaMapMarkerAlt, FaCity, FaFlag } from "react-icons/fa";
import { loadDistrictsByProvinceCode, loadProvinces, loadWardsByDistrictCode, Register } from "../configs/APIs";
import { useNavigate } from "react-router-dom";

const RegistrationForm = () => {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [provinces, setProvinces] = useState([]);
  const [districts, setDistricts] = useState([]);
  const [wards, setWards] = useState([]);
  const [selectedProvince, setSelectedProvince] = useState('');
  const [selectedDistrict, setSelectedDistrict] = useState('');
  const [selectedWard, setSelectedWard] = useState('');
  const nav = useNavigate();
  const [avatar, setAvatar] = useState(null);
  useEffect(() => {
    const fetchProvinces = async () => {
      const result = await loadProvinces();
      setProvinces(result);
    };
    fetchProvinces();
  }, []);

  // Load districts khi chọn province
  useEffect(() => {
    if (selectedProvince) {
      const fetchDistricts = async () => {
        const result = await loadDistrictsByProvinceCode(selectedProvince);
        setDistricts(result);
        setWards([]); // Xóa wards khi province thay đổi
      };
      fetchDistricts();
    }
  }, [selectedProvince]);

  // Load wards khi chọn district
  useEffect(() => {
    if (selectedDistrict) {
      const fetchWards = async () => {
        const result = await loadWardsByDistrictCode(selectedDistrict);
        setWards(result);
      };
      fetchWards();
    }
  }, [selectedDistrict]);

  const onSubmit = async (data) => {
    setIsSubmitting(true);
    // Simulating API call
    data.avatar = avatar;
    await new Promise((resolve) => setTimeout(resolve, 1000));
    console.log(data);
    try {
      const response = await Register(data);
      if (response.status === 201) {
        alert('Registration successful');
        nav('/')
      } else {
        alert('Registration failed');
      }
    } catch (err) {
      alert('An error occurred during registration');
    }
    setIsSubmitting(false);
  };

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    console.log(file);
    if (file) {
      setAvatar(file);
    } else {
      toast.error("Vui lòng thêm avatar");
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-100 to-purple-100 flex items-center justify-center p-4">
      <div className="bg-white rounded-lg shadow-xl p-8 w-full max-w-md">
        <h2 className="text-3xl font-bold text-center mb-8 text-gray-800">Register</h2>
        <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
          <div className="relative">
            <FaUser className="absolute top-3 left-3 text-gray-400" />
            <input
              type="text"
              {...register("username", { required: "Username is required" })}
              className={`w-full pl-10 pr-3 py-2 rounded-lg border ${errors.username ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
              placeholder="Username"
            />
            {errors.username && <p className="text-red-500 text-xs mt-1">{errors.username.message}</p>}
          </div>

          <div className="relative">
            <FaLock className="absolute top-3 left-3 text-gray-400" />
            <input
              type="password"
              {...register("password", { required: "Password is required", minLength: { value: 8, message: "Password must be at least 8 characters" } })}
              className={`w-full pl-10 pr-3 py-2 rounded-lg border ${errors.password ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
              placeholder="Password"
            />
            {errors.password && <p className="text-red-500 text-xs mt-1">{errors.password.message}</p>}
          </div>

          <div className="flex space-x-4">
            <div className="flex-1">
              <input
                type="text"
                {...register("firstName", { required: "First name is required" })}
                className={`w-full px-3 py-2 rounded-lg border ${errors.firstName ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
                placeholder="First Name"
              />
              {errors.firstName && <p className="text-red-500 text-xs mt-1">{errors.firstName.message}</p>}
            </div>
            <div className="flex-1">
              <input
                type="text"
                {...register("lastName", { required: "Last name is required" })}
                className={`w-full px-3 py-2 rounded-lg border ${errors.lastName ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
                placeholder="Last Name"
              />
              {errors.lastName && <p className="text-red-500 text-xs mt-1">{errors.lastName.message}</p>}
            </div>
          </div>

          <div className="relative">
            <FaMapMarkerAlt className="absolute top-3 left-3 text-gray-400" />
            <input
              type="text"
              {...register("address", { required: "Address is required" })}
              className={`w-full pl-10 pr-3 py-2 rounded-lg border ${errors.address ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
              placeholder="Address"
            />
            {errors.address && <p className="text-red-500 text-xs mt-1">{errors.address.message}</p>}
          </div>

          <div className="relative">
            <FaPhone className="absolute top-3 left-3 text-gray-400" />
            <input
              type="tel"
              {...register("phone", { required: "Phone number is required", pattern: { value: /^\d+$/, message: "Please enter a valid phone number" } })}
              className={`w-full pl-10 pr-3 py-2 rounded-lg border ${errors.phone ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
              placeholder="Phone Number"
            />
            {errors.phone && <p className="text-red-500 text-xs mt-1">{errors.phone.message}</p>}
          </div>

          <div className="relative">
            <FaEnvelope className="absolute top-3 left-3 text-gray-400" />
            <input
              type="email"
              {...register("email", { required: "Email is required", pattern: { value: /^\S+@\S+$/i, message: "Please enter a valid email" } })}
              className={`w-full pl-10 pr-3 py-2 rounded-lg border ${errors.email ? 'border-red-500' : 'border-gray-300'} focus:outline-none focus:ring-2 focus:ring-blue-500`}
              placeholder="Email"
            />
            {errors.email && <p className="text-red-500 text-xs mt-1">{errors.email.message}</p>}
          </div>

          {/* Province Dropdown */}
          <div className="mb-4">
            <label htmlFor="province" className="block text-sm font-medium text-gray-700 mb-2">Province</label>
            <select
              id="province"
              {...register("provinceCode", { required: "Province is required" })}
              value={selectedProvince}
              onChange={(e) => setSelectedProvince(e.target.value)}
              className="block w-full pl-3 pr-10 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
            >
              <option value="">Select Province</option>
              {provinces.map(province => (
                <option key={province.code} value={province.code}>
                  {province.fullName}
                </option>
              ))}
            </select>
            {errors.provinceCode && <p className="text-red-500 text-xs mt-1">{errors.provinceCode.message}</p>}
          </div>

          {/* District Dropdown */}
          {selectedProvince && (
            <div className="mb-4">
              <label htmlFor="district" className="block text-sm font-medium text-gray-700 mb-2">District</label>
              <select
                id="district"
                {...register("districtCode", { required: "District is required" })}
                value={selectedDistrict}
                onChange={(e) => setSelectedDistrict(e.target.value)}
                className="block w-full pl-3 pr-10 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
              >
                <option value="">Select District</option>
                {districts.map(district => (
                  <option key={district.code} value={district.code}>
                    {district.fullName}
                  </option>
                ))}
              </select>
              {errors.districtCode && <p className="text-red-500 text-xs mt-1">{errors.districtCode.message}</p>}
            </div>
          )}

          {/* Ward Dropdown */}
          {selectedDistrict && (
            <div className="mb-4">
              <label htmlFor="ward" className="block text-sm font-medium text-gray-700 mb-2">Ward</label>
              <select
                id="ward"
                {...register("wardCode", { required: "Ward is required" })}
                value={selectedWard}
                onChange={(e) => setSelectedWard(e.target.value)}
                className="block w-full pl-3 pr-10 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
              >
                <option value="">Select Ward</option>
                {wards.map(ward => (
                  <option key={ward.code} value={ward.code}>
                    {ward.fullName}
                  </option>
                ))}
              </select>
              {errors.wardCode && <p className="text-red-500 text-xs mt-1">{errors.wardCode.message}</p>}
            </div>
          )}
          <div>
            <label htmlFor="avatar" className="block text-sm font-medium text-gray-700 mb-2">Avatar</label>
            <input
              type="file"
              id="avatar"
              name="avatar"
              accept="image/*"
              onChange={handleAvatarChange}
              className="w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-blue-50 file:text-blue-700 hover:file:bg-blue-100"
            />
            {avatar && (
              <div className="mt-2 flex justify-center">
                <img src={URL.createObjectURL(avatar)} alt="Avatar preview" className="w-24 h-24 object-cover rounded-full border-4 border-blue-500" />
              </div>
            )}
          </div>

          <button
            type="submit"
            disabled={isSubmitting}
            className={`w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-200 ${isSubmitting ? 'opacity-50 cursor-not-allowed' : ''}`}
          >
            {isSubmitting ? 'Registering...' : 'Register'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default RegistrationForm;