const axiosInstance = axios.create({
    baseURL: 'http://localhost:8081/'
});

axiosInstance.interceptors.request.use(config => {
    const token = localStorage.getItem('user-token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default axiosInstance;