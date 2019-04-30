var app = new Vue({
    el: '#app',
    data: {
        recentBlocks: [],
        recentTransactions: [],
        bollen: true,
        bollen2: false,
        page1: 5,
        page2: 5,
    },
    mounted() {
        console.log('view mounted');
        this.getBlockRecent(this.page1);
        setInterval(this.tishi, 3000);
    },
    methods: {
        tishi() {
            var address = app.recentBlocks[0].hash;
            axios.get('http://localhost:8090/misc/importFromnextHash',
                {
                    params: {
                        blockhash: address,
                        isClean: false
                    }
                }
            )
                .then(function (response) {
                    axios.get('http://localhost:8090/block/getRecentBlocksById',
                        {
                            params: {
                                page1: 5
                            }
                        }
                    )
                        .then(function (response) {
                            // handle success
                            app.bollen = true;
                            app.bollen2 = false;
                            console.log(response);
                            app.recentBlocks = response.data;
                            var now = Date.now();
                            app.recentBlocks.forEach(block => {
                                block.showtime = parseInt((now - block.time) / 1000 / 60) + "minute";
                                block.showSizeOnDisk = block.sizeOnDisk.toLocaleString('en');
                            });
                            return this.recentBlocks;
                        })
                        .catch(function (error) {
                            // handle error
                            console.log(error);
                        });
                })
                .catch(function (error) {

                });
        },
        getBlockRecent(page1) {
            axios.get('http://localhost:8090/block/getRecentBlocksById',
                {
                    params: {
                        page1: page1
                    }
                }
            )
                .then(function (response) {
                    // handle success
                    app.bollen = true;
                    app.bollen2 = false;
                    console.log(response);
                    app.recentBlocks = response.data;
                    var now = Date.now();
                    app.recentBlocks.forEach(block => {
                        block.showtime = parseInt((now - block.time) / 1000 / 60) + "minute";
                        block.showSizeOnDisk = block.sizeOnDisk.toLocaleString('en');
                    });
                    return this.recentBlocks;
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                });
        }, TransactionList() {
            app.recentBlocks = [];
            app.bollen = false;
            app.bollen2 = true;
            axios.get('http://localhost:8090/transaction/getRecentTransactionsById',
                {
                    params: {
                        page2: this.page2
                    }
                })
                .then(function (response) {
                    // handle success
                    console.log(response);
                    app.recentTransactions = response.data;
                    var now = Date.now();
                    app.recentTransactions.forEach(block => {
                        block.showtime = parseInt((now - block.time) / 1000 / 60) + "minute";;
                        block.money = parseInt(block.amount * 500);
                    });
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                });
        }
    }
})