const instance = axios.create({
    baseUrl: "http:localhost:8081/api",
    timeout: 1000,
    headers: {"Content-type": "application/json"},
});
export default instance;