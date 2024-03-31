const viewstatementButton = document.getElementById('viewstatement');
const transactionTable = document.querySelector('.transaction-table');

viewstatementButton.addEventListener('click', () => {
    if (transactionTable.style.display === 'none' || transactionTable.style.display === '') {
        transactionTable.style.display = 'table';
    } else {
        transactionTable.style.display = 'none';
    }
});