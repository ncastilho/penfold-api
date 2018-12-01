let path = require('path');

module.exports = {
    entry: './src/main/js/App.js',
    devtool: 'sourcemaps',
    cache: false,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/build/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    }
};