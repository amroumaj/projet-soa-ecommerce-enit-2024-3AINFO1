const express = require ('express');
const cartesbancairesRoutes = require('./cartesbancaires/routes');
const pool = require('./db');

const port = 8085;

const app = express();

app.use(express.json());
app.use('/payment/cartesbancaires', cartesbancairesRoutes);




//receiving the Amount and sending it to the bank
/* fetch('http://localhost:8084/')
    .then(response => response.json())
    .then(data => app.get('/:data.OrderId', async (req, res) => {
                    try {
                        const amount = parseInt('data.amount')
                    } catch (err) {
                        console.log(err)
                        res.sendStatus(500)
                    }})); */

app.listen(port, () => {
    console.log(`Payment service listening on port ${port}`);
})