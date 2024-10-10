import axios from "axios";
import cookie from "react-cookies";

const BASE_URL = "http://localhost:8080/EcommerceWebsite";
// const BASE_URL = "https://ecommerce-xbbg.onrender.com";


export const endpoints = {
  'products': "/api/products/"
}

export default axios.create({
  baseURL: BASE_URL
});


export const paymentVNPay = async (amount) => {
  try {
    const response = await axios.get(`${BASE_URL}/api/create-payment/?amount=${amount}`);
    return response;
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
};


export const createSaleOrder = async (saleOrderRequest) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/orders/`, saleOrderRequest);
    return response.data;
  } catch (error) {
    console.error("Error creating sale order:", error);
    throw error;
  }
};


export const updateDefaultAddress = async (shippingAddressId, username) => {
  try {
    const response = await axios.put(`${BASE_URL}/api/addresses/default/${shippingAddressId}?username=${username}`);
    return response.data;
  } catch (error) {
    console.error("Error update default address:", error);
    throw error;
  }
};
export const createShippingAddress = async (shippingAddress) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/addresses/`, shippingAddress);
    return response.data;
  } catch (error) {
    console.error("Error create shipping address:", error);
    throw error;
  }
};

export const getAllShippingAddresses = async (username) => {
  try {
    const response = await axios.get(`${BASE_URL}/api/addresses/${username}`)
    return response;
  } catch (error) {
    // Xử lý lỗi và có thể ném ra để xử lý ở nơi gọi
    throw new Error(error.response?.data?.message || 'Error updating user');
  }
}

export const updateInfoUser = async (username, userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/users/update/${username}`, userData, {
      headers: {
        "Content-Type": `multipart/form-data`
      },
    });
    return response;
  } catch (error) {
    // Xử lý lỗi và có thể ném ra để xử lý ở nơi gọi
    throw new Error(error.response?.data?.message || 'Error updating user');
  }
};


export const requestOTP = async (username) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/forgot-password/verifyAccount/${username}`);
    return response.data;
  } catch (error) {

    throw error.response ? error.response.data : error.message;
  }
};

export const verifyOtp = async (otp, username) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/forgot-password/verifyOtp/${otp}/${username}`);
    return response.data;
  } catch (error) {
    throw error.response ? error.response.data : error.message;
  }
};

export const createNewPassword = async (username, newPassword, confirmPassword) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/forgot-password/create-new-password/${username}`, {
      newPassword,
      confirmPassword
    });
    return response.data;
  } catch (error) {
    throw error.response ? error.response.data : error.message;
  }
};

export const changePassword = async (username, oldPassword, newPassword, confirmPassword) => {
  try {
    const response = await axios.post(`${BASE_URL}/api/change-password/${username}`, {
      oldPassword,
      newPassword,
      confirmPassword
    });
    return response.data;
  } catch (error) {
    throw error.response ? error.response.data : error.message;
  }
};



export const addRecentlyViewedByUser = async (username, productId) => {
  try {
    const res = await axios.post(`${BASE_URL}/api/recently-viewed/add/?username=${username}&productId=${productId}`)
    return res;
  } catch (err) {
    return err;
  }
}

export const getRecentlyViewed = async (username) => {
  try {
    const res = await axios.get(`${BASE_URL}/api/recently-viewed/user/${username}/`)
    return res;
  } catch (err) {
    return err;
  }
};


export const fetchCartItems = async (username) => {
  try {
    const res = await axios.get(`${BASE_URL}/api/cart/items/?username=${username}`, {
      headers: {
        Authorization: cookie.load("token")
      }
    });
    return res.data; // Giả sử bạn chỉ cần dữ liệu từ response
  } catch (err) {
    return err;
  }
};

export const addToCart = async (username, productId, quantity) => {
  try {
    const res = await axios.post(`${BASE_URL}/api/cart/add/?username=${username}&productId=${productId}&quantity=${quantity}`, {
      headers: {
        Authorization: cookie.load("token")
      }
    });
    return res;
  } catch (err) {
    return err;
  }
};

export const updateCartQuantity = async (username, productId, quantity) => {
  try {
    const res = await axios.post(`${BASE_URL}/api/cart/update/?username=${username}&productId=${productId}&quantity=${quantity}`, {
      headers: {
        Authorization: cookie.load("token")
      }
    });
    return res;
  } catch (err) {
    return err;
  }
};

export const removeFromCart = async (username, productId) => {
  try {
    const res = await axios.delete(`${BASE_URL}/api/cart/remove/${username}/${productId}/`, {
      headers: {
        Authorization: cookie.load("token")
      }
    });
    return res;
  } catch (err) {
    return err;
  }
};

export const clearCart = async (username) => {
  try {
    const res = await axios.delete(`${BASE_URL}/api/cart/clear/${username}/`);
    return res;
  } catch (err) {
    return err;
  }
};


export const productDetail = async (id) => {
  try {
    const res = await axios.get(`${BASE_URL}/api/product/${id}/`)
    return res
  } catch (err) {
    return err
  }
}


export const loginAccount = async (username, password) => {
  try {
    const res = await axios.post(`${BASE_URL}/api/login/`, {
      username: username,
      password: password,
    });
    return res;
  } catch (ex) {
    // console.log(ex)
  }
};

export const getCurrentUser = async () => {
  try {
    const res = await axios.get(
      `${BASE_URL}/api/current-user/`,
      {
        headers: {
          Authorization: cookie.load("token"),
        },
      }
    );
    if (res.status === 200) return res;
  } catch (ex) {
    console.error(ex);
  }
};

export const loadCarrier = async () => {
  try {
    const res = await axios.get(`${BASE_URL}/api/carrier/`);
    return res.data;
  } catch (err) {
    console.error("Failed to load provinces:", err);
    return err;
  }
};

export const loadProvinces = async () => {
  try {
    const res = await axios.get(`${BASE_URL}/api/provinces/`);
    return res.data;
  } catch (err) {
    console.error("Failed to load provinces:", err);
    return err;
  }
};

export const loadDistrictsByProvinceCode = async (provinceCode) => {
  try {
    const res = await axios.get(`${BASE_URL}/api/province/${provinceCode}/districts/`);
    return res.data;
  } catch (err) {
    console.error(`Failed to load districts for province code ${provinceCode}:`, err);
    return err;
  }
};

export const loadWardsByDistrictCode = async (districtCode) => {
  try {
    const res = await axios.get(`${BASE_URL}/api/district/${districtCode}/wards/`);
    return res.data;
  } catch (err) {
    console.error(`Failed to load wards for district code ${districtCode}:`, err);
    return err;
  }
};

export const loadCategories = async () => {
  try {
    const res = await axios.get(`${BASE_URL}/api/categories/`);
    return res;
  } catch (err) {
    console.error("Failed to load categories:", err);
    return err;
  }
};

export const loadBrands = async () => {
  try {
    const res = await axios.get(`${BASE_URL}/api/brands/`);
    return res;
  } catch (err) {
    console.error("Failed to load brands:", err);
    return err;
  }
};

// export const loadProducts = async () => {
//   try {
//     const res = await axios.get(`${BASE_URL}/products/`)
//     return res;
//   } catch (err) {
//     console.error("Failed to load products:", err);
//     return err;
//   }
// };

export const Register = async (data) => {
  try {
    const res = await axios.post(`${BASE_URL}/api/register/`,
      data,
      {
        headers: {
          "Content-Type": `multipart/form-data`
        },
      }
    );
    return res;
  } catch (err) {
    return err;
  }
};