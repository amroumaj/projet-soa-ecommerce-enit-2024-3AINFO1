require('dotenv').config();  // Load environment variables from .env
const express = require('express');
const { Sequelize } = require('sequelize');

// Log environment variables to check if they are loaded properly

// Create an Express application
const app = express();
const PORT = process.env.PORT || 8082;

// Set up Sequelize to use the DATABASE_URL connection string
const sequelize = new Sequelize(process.env.DATABASE_URL, {
    dialect: 'postgres',
    protocol: 'postgres',
    logging: console.log,  // Optional: logs SQL queries to the console
});



// Function to test database connection
async function testDatabaseConnection() {
    try {
        await sequelize.authenticate();
        console.log('Database connection established successfully.');
    } catch (error) {
        console.error('Unable to connect to the database:', error);
    }
}

// Test the connection when the server starts
testDatabaseConnection();

// Define a sample route
app.get('/', (req, res) => {
    res.send('Catalog service is running');
});

// Start the server
app.listen(PORT, () => {
    console.log(`Catalog service listening on port ${PORT}`);
});
