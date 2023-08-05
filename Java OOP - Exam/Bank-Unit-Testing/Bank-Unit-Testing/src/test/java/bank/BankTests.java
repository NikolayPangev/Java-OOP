package bank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class BankTests {
    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank("DSK", 3);
    }

    @Test
    public void testCreateBank() {
        Assert.assertEquals("DSK", bank.getName());
        Assert.assertEquals(3, bank.getCapacity());
        Assert.assertEquals(0, bank.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWithNullName(){
        bank = new Bank(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWithEmptyName(){
        bank = new Bank("  ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWithInvalidCapacity(){
        bank = new Bank("OBB", -1);
    }

    @Test
    public void testAddClient() {
        Client client = new Client("Kalina");
        bank.addClient(client);
        assertEquals(1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientExceedCapacity() {
        bank.addClient(new Client("Kalina"));
        bank.addClient(new Client("Simona"));
        bank.addClient(new Client("Nikolay"));
        bank.addClient(new Client("Samuil"));
    }

    @Test
    public void testRemoveClient() {
        Client client = new Client("Kalina");
        bank.addClient(client);
        assertEquals(1, bank.getCount());

        bank.removeClient("Kalina");
        assertEquals(0, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveClientNonExistent() {
        bank.removeClient("Client does nor exists");
    }

    @Test
    public void testLoanWithdrawal() {
        Client client = new Client("Kalina");
        bank.addClient(client);

        client.setApprovedForLoan(true);
        assertEquals(true, client.isApprovedForLoan());

        bank.loanWithdrawal("Kalina");
        assertEquals(false, client.isApprovedForLoan());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawalNonExistent() {
        bank.loanWithdrawal("Client does not exists");
    }

    @Test
    public void testStatistics() {
        bank.addClient(new Client("Kalina"));
        bank.addClient(new Client("Simona"));
        bank.addClient(new Client("Nikolay"));

        assertEquals("The client Kalina, Simona, Nikolay is at the DSK bank!", bank.statistics());
    }
}


