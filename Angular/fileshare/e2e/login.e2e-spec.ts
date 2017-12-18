import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

const link = 'http://localhost:4200';

describe('Login functionality', () => {
    beforeEach(() => {
        browser.get('/login');
    });

    it('should be able to navigate to login', () => {
        expect(browser.getCurrentUrl()).toEqual(link + '/login').then(t => {}).catch(c => {});
    });

    it('should fail to log in for invalid credentials', () => {
        element(by.css('input[type="text"]')).sendKeys('dasdwa');
        element(by.css('input[type="password"]')).sendKeys('eiutzlkjsdzgl');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/login').then(t => {}).catch(c => {});
    });

    it('should fail to log in for banned user', () => {
        element(by.css('input[type="text"]')).sendKeys('test');
        element(by.css('input[type="password"]')).sendKeys('test1');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/login').then(t => {}).catch(c => {});
    });

    /*it('should to log in for valid credentials', () => {
        element(by.css('input[type="text"]')).sendKeys('admin');
        element(by.css('input[type="password"]')).sendKeys('admin');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/').then(t => {}).catch(c => {});
    });

    it('should get blank page at /login', () => {
        expect(element(by.css('input[type="text"]')).isDisplayed()).toEqual(false).then(t => {}).catch(c => {});
    });

    it('should get blank page at /register', () => {
        browser.get('/register');
        expect(element(by.css('input[type="text"]')).isDisplayed()).toEqual(false).then(t => {}).catch(c => {});
    });*/
});
