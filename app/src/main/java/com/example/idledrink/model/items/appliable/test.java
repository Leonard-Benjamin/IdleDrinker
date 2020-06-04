package com.example.idledrink.model.items.appliable;

interface ITest {
    void onTest();
}

class Foo {
    private ITest iTest;

    Foo (ITest iTest) {
        this.iTest = iTest;
    }

    void doAction() {
        this.iTest.onTest();
    }
}
