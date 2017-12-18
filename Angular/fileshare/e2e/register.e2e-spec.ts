import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

const link = 'http://localhost:4200';

describe('Register functionality', () => {
    beforeEach(() => {
        browser.get('/register');
    });

    it('should be able to navigate to register', () => {
        expect(browser.getCurrentUrl()).toEqual(link + '/register').then(t => {}).catch(c => {});
    });

    it('should fail to register for short username', () => {
        element(by.css('input[type="text"]')).sendKeys('ok');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.com');
        element(by.css('input[type="password"]')).sendKeys('12345');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should fail to register for short password', () => {
        element(by.css('input[type="text"]')).sendKeys('abc');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.com');
        element(by.css('input[type="password"]')).sendKeys('1234');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should fail to register for invalid e-mail', () => {
        element(by.css('input[type="text"]')).sendKeys('abc');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.a');
        element(by.css('input[type="password"]')).sendKeys('12345');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should fail to register for invalid e-mail 2nd', () => {
        element(by.css('input[type="text"]')).sendKeys('abc');
        element(by.css('input[type="email"]')).sendKeys('ab@abc.com');
        element(by.css('input[type="password"]')).sendKeys('12345');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should fail to register for invalid e-mail 3rd', () => {
        element(by.css('input[type="text"]')).sendKeys('abc');
        element(by.css('input[type="email"]')).sendKeys('abcabc.com');
        element(by.css('input[type="password"]')).sendKeys('12345');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should fail to register for invalid username (special character)', () => {
        element(by.css('input[type="text"]')).sendKeys('abc@');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.com');
        element(by.css('input[type="password"]')).sendKeys('12345');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should fail to register for invalid password (special character)', () => {
        element(by.css('input[type="text"]')).sendKeys('abc');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.com');
        element(by.css('input[type="password"]')).sendKeys('12345@');
        expect(browser.element(by.css('input[type="button"]')).isEnabled()).toEqual(false).then(t => {}).catch(c => {});;
    });

    it('should to register for valid credentials', () => {
        element(by.css('input[type="text"]')).sendKeys('test12');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.com');
        element(by.css('input[type="password"]')).sendKeys('test12');
        element(by.css('input[type="button"]')).click();
        browser.sleep(3000);
        expect(browser.getCurrentUrl()).toEqual(link + '/login').then(t => {}).catch(c => {});
    });

    it('should fail to register for occupied username', () => {
        element(by.css('input[type="text"]')).sendKeys('test12');
        element(by.css('input[type="email"]')).sendKeys('abc2@abc.com');
        element(by.css('input[type="password"]')).sendKeys('12345');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/register').then(t => {}).catch(c => {});
    });

    it('should fail to register for occupied e-mail', () => {
        element(by.css('input[type="text"]')).sendKeys('test100');
        element(by.css('input[type="email"]')).sendKeys('abc@abc.com');
        element(by.css('input[type="password"]')).sendKeys('12345');
        element(by.css('input[type="button"]')).click();
        expect(browser.getCurrentUrl()).toEqual(link + '/register').then(t => {}).catch(c => {});
    });
});
