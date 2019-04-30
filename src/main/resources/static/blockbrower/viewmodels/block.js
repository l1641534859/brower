var app = new Vue({
    el: '#app',
    data: {
        block: '',
        out:"",
    },
    computed: {

    },
    mounted() {
        console.log('view mounted');
        var url = new URL(location.href);
        var split = url.href.split('/');
        var address = split[split.length - 1].substring(6, split[split.length - 1].length);
        this.getblock(address);
    },
    methods: {
        getblock(address) {
            axios.get('http://localhost:8090/block/getBlockByAddress', {
                params: {
                    address: address
                }
            }
            )
                .then(function (response) {
                    console.log(response);
                    app.block = response.data;
                    var sum=0;
                    app.block.transactions.map(b=>{
                        b.transactionlist.map(bb=>{
                            sum+=bb.amount
                        })  
                    })
                    app.out=sum;
                    app.block.transactionFees=sum*0.1
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                });
        }
    }
})