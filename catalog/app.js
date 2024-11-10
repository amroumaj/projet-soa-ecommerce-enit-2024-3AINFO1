require('dotenv').config();
const express = require('express');
const app = express();

const PORT = process.env.PORT || 8082;

app.get('/', (req, res) => {
  res.send('Catalog Microservice Running');
});

app.listen(PORT, () => {
  console.log(`Catalog service is running on port ${PORT}`);
});

const Product = require('./models/Product');
const sequelize = require('./models/index');

sequelize.sync().then(() => {
  console.log('Database synchronized');
}).catch((err) => {
  console.error('Failed to sync database:', err);
});

app.post('/products', async (req, res) => {
    try {
      const product = await Product.create(req.body);
      res.status(201).json(product);
    } catch (err) {
      res.status(400).json({ error: err.message });
    }
  });
  