const instance = axios.create({
    baseUrl: "http:localhost:8081/api",
    timeout: 5000,
    headers: {"Content-type": "application/json"},
});
export default instance;