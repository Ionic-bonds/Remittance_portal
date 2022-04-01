<template>
  <div class="transactions">
    <div class="transactions--header">
      <div class="transactions--header--left">
        <div class="transactions--header--left--title">
          <h1>Transactions</h1>
        </div>
        <div class="transactions--header--left--search">
          <input type="text" placeholder="Search" />
        </div>
      </div>
      <div class="transactions--header--right">
        <div class="transactions--header--right--add">
          <button class="btn btn-primary">Add Transaction</button>
        </div>
      </div>
    </div>
    <div class="transactions--body">
      <div class="transactions--body--left">
        <div class="transactions--body--left--title">
          <h2>Transactions</h2>
        </div>
        <div class="transactions--body--left--list">
          <div class="transactions--body--left--list--item">
            <div class="transactions--body--left--list--item--title">
              <h3>Transaction 1</h3>
            </div>
            <div class="transactions--body--left--list--item--details">
              <div class="transactions--body--left--list--item--details--date">
                <p>Date:</p>
              </div>
              <div
                class="transactions--body--left--list--item--details--amount"
              >
                <p>Amount:</p>
              </div>
              <div
                class="transactions--body--left--list--item--details--category"
              >
                <p>Category:</p>
              </div>
              <div
                class="transactions--body--left--list--item--details--description"
              >
                <p>Description:</p>
              </div>
            </div>
          </div>
          <div class="transactions--body--left--list--item">
            <div class="transactions--body--left--list--item--title">
              <h3>Transaction 2</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
