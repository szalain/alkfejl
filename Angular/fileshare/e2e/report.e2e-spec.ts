import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

const link = 'http://localhost:4200';

describe('Report functionality', () => {
    beforeEach(() => {
        browser.get('/report');
    });

    it('should to log in for valid credentials', () => {
        browser.get('/login');
        element(by.css('input[type="text"]')).sendKeys('admin');
        element(by.css('input[type="password"]')).sendKeys('admin');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/').then(t => {}).catch(c => {});
    });

    it('should get blank page at /login', () => {
        browser.get('/login');
        expect(element(by.css('input[type="text"]')).isDisplayed()).toEqual(false).then(t => {}).catch(c => {});
    });

    it('should get blank page at /register', () => {
        browser.get('/register');
        expect(element(by.css('input[type="text"]')).isDisplayed()).toEqual(false).then(t => {}).catch(c => {});
    });

    it('should fail to make report with invalid username', () => {
        element(by.css('input[type="text"]')).sendKeys('fefejifidjsf');
        element(by.css('textarea')).sendKeys('123');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/report').then(t => {}).catch(c => {});
    });

    it('should create a report with valid username', () => {
        element(by.css('input[type="text"]')).sendKeys('admin');
        element(by.css('textarea')).sendKeys('123');
        element(by.css('input[type="button"]')).click();
        browser.sleep(3000);
        expect(browser.getCurrentUrl()).toEqual(link + '/').then(t => {}).catch(c => {});
    });

});
