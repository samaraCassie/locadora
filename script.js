const form = document.querySelector('form');
const result = document.getElementById('result');
const inputModel = document.getElementById('model');
const inputPrice = document.getElementById('price');
const btnListar = document.getElementById('listarCadastros');
const table = document.getElementById('result');


const listaCarros = [];

function cadastrarCarro(model, price) {
    console.log(model, price);
    if (!model && !price && price <= 0) {
        alert(`Informe dados válidos`);
        inputModel.focus();
        return;
    }
    listaCarros.push({
        model: model,
        price: price
    });
    inputModel.value = '';
    inputPrice.value = 0;
    console.log(listaCarros);
}form.addEventListener('submit', (e) => {
    e.preventDefault();

    const model = form.model.value;
    const price = Number(form.price.value);

    cadastrarCarro(model, price);
});  


function listarCadastros() {
    table.innerHTML = ``;
    listaCarros.forEach(carro => {
        const tr = document.createElement('tr');
        const tdModel = document.createElement('td');
        tdModel.innerHTML = `Modelo: ${carro.model}`;
        const tdPrice = document.createElement('td');
        tdPrice.innerHTML = `Preço: ${carro.price}`;
        tr.appendChild(tdModel);
        tr.appendChild(tdPrice);
        table.appendChild(tr);
        console.log(table);
    });
}btnListar.addEventListener('click', listarCadastros);