module.exports = {
    devServer: {
        open:true,
        host: 'localhost',
        port: 8080,
        proxy: {
            '/api': {
                target: 'http://localhost:80',
                changeOrigin: true,
                ws: true
            }
        }
    }
}