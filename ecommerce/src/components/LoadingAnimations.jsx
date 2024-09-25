import React, { useState, useEffect } from "react";

const LoadingAnimations = () => {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setLoading(false);
    }, 3000);
    return () => clearTimeout(timer);
  }, []);

  const renderLoadingAnimation = () => {
    return (
      <div className="flex flex-col items-center">
        <div className="w-12 h-12 border-4 border-gray-200 rounded-full animate-spin border-t-blue-500"></div>
        <p className="mt-4 text-gray-600">Loading...</p>
      </div>
    );
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center">
      {loading ? renderLoadingAnimation() : renderLoadingAnimation()}
    </div>
  );
};

export default LoadingAnimations;
