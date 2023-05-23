/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.businessobject;

import static org.junit.Assert.fail;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BillTest {
    private Bill bill = new Bill();

    private BillBreakDown billBreakDown = new BillBreakDown();

    private static final String VERY_LARGE_TEXT = "Maecenas vitae vulputate augue. Maecenas elementum interdum dignissim. Aliquam cursus rutrum risus. Proin accumsan ultrices enim, eget cursus sem interdum et. Sed eleifend arcu in lacus euismod aliquet. Praesent luctus neque eget neque vehicula bibendum. Suspendisse at sem ac justo vulputate gravida. Duis a sapien augue, eu suscipit nibh. Etiam congue viverra felis, vel ornare lectus dignissim quis. Maecenas eleifend convallis mi, vel cursus eros semper id. Nam pulvinar arcu sed quam euismod sit amet semper augue hendrerit. Nunc convallis mi eu orci egestas scelerisque. Vestibulum pulvinar mattis lacus non consectetur. Nam lobortis pellentesque orci ac euismod. Aenean cursus, risus eu hendrerit mollis, nulla erat accumsan nisl, in aliquam ligula arcu a nisi. Suspendisse sapien sapien, pretium a porta eu, laoreet in urna. Integer sit amet tortor sed diam pretium molestie. Nullam sed felis sit amet metus facilisis lacinia sit amet a augue. Donec faucibus dolor a ipsum egestas a ornare ligula hendrerit. Suspendisse elementum tristique vulputate. Praesent porta rutrum velit at consequat. Nulla facilisi. Curabitur sollicitudin lectus ac felis commodo nec tristique nibh ultricies. Sed at enim at magna rutrum sagittis. Nulla blandit faucibus ligula in bibendum. Cras metus magna, volutpat sit amet molestie et, imperdiet sit amet diam. Sed accumsan mollis ligula sit amet dapibus. Integer orci nibh, malesuada pretium pulvinar eget, sodales a magna. Pellentesque id sollicitudin ligula. Nullam et arcu ac nisi fermentum vulputate. Etiam tristique vulputate condimentum. Cras luctus, mauris id fringilla dapibus, diam nibh sodales arcu, at luctus ligula ipsum eget lacus. Nam ipsum metus, pellentesque nec varius sed, consectetur ut magna. Proin tortor lacus, volutpat eu hendrerit in, ultricies at turpis. Pellentesque nunc nibh, congue et aliquam non, dapibus et justo. Aliquam consequat arcu quis augue euismod eget pellentesque nisl vulputate. Pellentesque elementum vulputate libero a volutpat. Pellentesque et elit est. Donec fermentum porta elit, at ultricies massa tempor at. Sed purus diam, tempus eget mattis vitae, tempus a justo. Maecenas porta, nisi ac suscipit rhoncus, ipsum nulla laoreet felis, luctus hendrerit dolor sem viverra magna. Fusce tristique lectus non purus imperdiet a faucibus lacus ultricies. Duis non elit eros. Morbi non nisl id tortor lobortis facilisis vehicula quis nisl. Proin euismod turpis non orci tempor et lacinia nisi rutrum. Donec quis odio leo, ac laoreet turpis. Pellentesque sed justo velit. Ut nec euismod felis. Duis pulvinar laoreet quam, eu pellentesque dui auctor vitae. Mauris eget sapien sed lorem posuere sagittis sit amet ut velit. Integer aliquam aliquam venenatis. Aenean non lectus non dui euismod porta ut eu sapien. Aliquam condimentum sapien augue. Donec eros tellus, hendrerit nec volutpat ut, cursus in lectus. Quisque ultricies augue non nibh lacinia suscipit. Aliquam urna ligula, pretium a scelerisque non, vehicula sed quam. Aenean aliquet felis dignissim tellus euismod consequat. Suspendisse nec diam sapien. Donec vulputate aliquam quam eget euismod. Duis nec ante sed velit mollis vehicula. Nullam sit amet viverra est. Sed velit enim, viverra eget malesuada at, luctus ac metus. Etiam egestas rutrum nisl, quis pulvinar mauris fringilla eu. Pellentesque enim metus, adipiscing et malesuada nec, feugiat ut arcu. Aliquam vel elit erat. Fusce nunc lorem, fringilla id sagittis vel, sodales in nunc. Nullam vel sodales justo. Donec molestie sollicitudin vulputate. Maecenas iaculis vulputate neque, et eleifend dui auctor nec. Proin quis sem lectus. Proin commodo ultrices ipsum rutrum pellentesque. Curabitur sit amet nisl a tortor fringilla gravida rhoncus sit amet justo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut lacinia, metus at ultrices molestie, dolor erat imperdiet ante, vel viverra augue enim a leo. Integer lobortis iaculis massa, ac mattis justo faucibus ut. Etiam vitae justo turpis, ac hendrerit nisl. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Ut et magna elit, a iaculis felis. Curabitur dictum pharetra auctor. Duis non orci elit. Nulla nec lectus ac tellus adipiscing consequat. Phasellus lobortis rhoncus semper. Nullam nec sapien ut elit tincidunt porttitor id sit amet augue. Maecenas sed urna nec sapien venenatis convallis. Mauris malesuada, purus a interdum volutpat, orci sem ornare orci, non semper odio est faucibus ante. Aliquam eget nisi orci, eget mollis ligula. Duis ultricies varius sapien sed iaculis. Vestibulum ornare dolor ac sapien bibendum et varius nunc elementum. Praesent sed lectus elementum nibh pellentesque porttitor. Duis pellentesque porta tellus. Vestibulum tempus luctus ligula, sodales pulvinar enim lacinia ac. Mauris non justo eget ante aliquam dignissim vitae sed eros. Duis in orci orci. Aenean tincidunt scelerisque convallis. Nam eu pretium tortor. Morbi ac urna in risus viverra vehicula sed quis magna. Phasellus sodales egestas viverra. Quisque condimentum volutpat sagittis. Nam dapibus sapien ac nisi fermentum interdum. Donec pharetra imperdiet lorem, nec tempus diam congue a. In volutpat varius congue. Quisque in tortor nulla. Quisque fringilla lacus quis tortor mattis lacinia. Etiam viverra sapien sit amet odio porta in accumsan mauris condimentum. In diam purus, hendrerit vel pulvinar quis, sagittis nec ipsum. Sed non sapien ac justo imperdiet pretium a sit amet velit. Suspendisse condimentum orci at est blandit et porttitor nulla viverra. Donec hendrerit tempus lorem, ut posuere odio egestas ut. Duis venenatis augue ut libero sagittis mollis. Maecenas sit amet arcu eget nibh interdum commodo. Vestibulum consectetur mi eu lacus suscipit ultrices condimentum purus ultricies. Sed sit amet ipsum eu purus sagittis mollis et at elit. Suspendisse molestie arcu eu lacus consectetur rutrum. Pellentesque consequat fermentum egestas. Proin a ultrices mauris. Quisque suscipit elementum lectus, id placerat risus gravida eu. Curabitur mollis justo a erat lacinia id molestie sapien pharetra. Donec molestie dictum erat vel pharetra. Suspendisse justo eros, sagittis a laoreet vel, consectetur eu tellus. Sed auctor dapibus est, et sagittis est rutrum sed. In ullamcorper iaculis luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris mollis dapibus mi vel faucibus. Curabitur vel metus a magna consequat tincidunt ut ut nulla. Morbi euismod diam adipiscing diam convallis interdum. Ut malesuada eleifend tristique. Praesent vitae lorem id dolor aliquam fermentum. Morbi fermentum porta malesuada. Nullam a nisi at magna cursus ullamcorper ut in enim. Donec aliquet placerat eros, porttitor dignissim lorem consectetur porta. Quisque a tincidunt ligula. Pellentesque lacinia lobortis eros, sed blandit augue elementum quis. Nullam blandit hendrerit odio eu tempus. Vestibulum ut massa felis. Mauris ac velit libero. Donec id purus purus. Integer lobortis fermentum odio mattis tincidunt. Aenean quis urna risus, vitae adipiscing lectus. Nunc vitae elit non massa elementum gravida et nec dui. Nam laoreet venenatis augue ut sagittis. Cras id eros diam. Morbi lobortis egestas blandit. Proin ut lectus orci. Donec vehicula augue ac nunc accumsan adipiscing. Morbi scelerisque cursus ligula, ac scelerisque orci egestas eget. Morbi ornare lacinia sodales. Phasellus tincidunt magna ac elit feugiat in accumsan neque convallis. Cras cursus varius purus sed vulputate. Nunc libero augue, consectetur eget egestas ac, pellentesque non turpis.";

    private static final String MAX_TEXT = "Maecenas vitae vulputate augue. Maecenas elementum interdum dignissim. Aliquam cursus rutrum risus. Proin accumsan ultrices enim, eget cursus sem interdum et. Sed eleifend arcu in lacus euismod aliquet. Praesent luctus neque eget neque vehicula bibendum. Suspendisse at sem ac justo vulputate gravida. Duis a sapien augue, eu suscipit nibh. Etiam congue viverra felis, vel ornare lectus dignissim quis. Maecenas eleifend convallis mi, vel cursus eros semper id. Nam pulvinar arcu sed quam euismod sit amet semper augue hendrerit. Nunc convallis mi eu orci egestas scelerisque. Vestibulum pulvinar mattis lacus non consectetur. Nam lobortis pellentesque orci ac euismod. Aenean cursus, risus eu hendrerit mollis, nulla erat accumsan nisl, in aliquam ligula arcu a nisi. Suspendisse sapien sapien, pretium a porta eu, laoreet in urna. Integer sit amet tortor sed diam pretium molestie. Nullam sed felis sit amet metus facilisis lacinia sit amet a augue. Donec faucibus dolor a ipsum egestas a ornare ligula hendrerit. Suspendisse elementum tristique vulputate. Praesent porta rutrum velit at consequat. Nulla facilisi. Curabitur sollicitudin lectus ac felis commodo nec tristique nibh ultricies. Sed at enim at magna rutrum sagittis. Nulla blandit faucibus ligula in bibendum. Cras metus magna, volutpat sit amet molestie et, imperdiet sit amet diam. Sed accumsan mollis ligula sit amet dapibus. Integer orci nibh, malesuada pretium pulvinar eget, sodales a magna. Pellentesque id sollicitudin ligula. Nullam et arcu ac nisi fermentum vulputate. Etiam tristique vulputate condimentum. Cras luctus, mauris id fringilla dapibus, diam nibh sodales arcu, at luctus ligula ipsum eget lacus. Nam ipsum metus, pellentesque nec varius sed, consectetur ut magna. Proin tortor lacus, volutpat eu hendrerit in, ultricies at turpis. Pellentesque nunc nibh, congue et aliquam non, dapibus et justo. Aliquam consequat arcu quis augue euismod eget pellentesque nisl vulputate. Pellentesque elementum vulputate libero a volutpat. Pellentesque et elit est. Donec fermentum porta elit, at ultricies massa tempor at. Sed purus diam, tempus eget mattis vitae, tempus a justo. Maecenas porta, nisi ac suscipit rhoncus, ipsum nulla laoreet felis, luctus hendrerit dolor sem viverra magna. Fusce tristique lectus non purus imperdiet a faucibus lacus ultricies. Duis non elit eros. Morbi non nisl id tortor lobortis facilisis vehicula quis nisl. Proin euismod turpis non orci tempor et lacinia nisi rutrum. Donec quis odio leo, ac laoreet turpis. Pellentesque sed justo velit. Ut nec euismod felis. Duis pulvinar laoreet quam, eu pellentesque dui auctor vitae. Mauris eget sapien sed lorem posuere sagittis sit amet ut velit. Integer aliquam aliquam venenatis. Aenean non lectus non dui euismod porta ut eu sapien. Aliquam condimentum sapien augue. Donec eros tellus, hendrerit nec volutpat ut, cursus in lectus. Quisque ultricies augue non nibh lacinia suscipit. Aliquam urna ligula, pretium a scelerisque non, vehicula sed quam. Aenean aliquet felis dignissim tellus euismod consequat. Suspendisse nec diam sapien. Donec vulputate aliquam quam eget euismod. Duis nec ante sed velit mollis vehicula. Nullam sit amet viverra est. Sed velit enim, viverra eget malesuada at, luctus ac metus. Etiam egestas rutrum nisl, quis pulvinar mauris fringilla eu. Pellentesque enim metus, adipiscing et malesuada nec, feugiat ut arcu. Aliquam vel elit erat. Fusce nunc lorem, fringilla id sagittis vel, sodales in nunc. Nullam vel sodales justo. Donec molestie sollicitudin vulputate. Maecenas iaculis vulputate neque, et eleifend dui auctor nec. Proin quis sem lectus. Proin commodo ultrices ipsum rutrum pellentesque. Curabitur sit amet nisl a tortor fringilla gravida rhoncus sit amet justo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut lacinia, metus at ultrices molestie, dolor erat imperdiet ante, vel viverra augue enim a leo. Integer lobortis iaculis massa, ac mattis justo faucibus ut. Etiam vitae justo turpis, ac hendrerit nisl. Vestibulum ante ipsum primis in faucibus o";

    @Test
    public void testBillNameLength() {
        bill.setName(VERY_LARGE_TEXT);

        if (!bill.getName().equals(MAX_TEXT)) {
            fail("el nombre deberia tener una longitud de " + Bill.MAX_LENGTH + " caracteres, pero tiene " + bill.getName().length());
        }
    }

    @Test
    public void testBillObservationsLength() {
        bill.setObservations(VERY_LARGE_TEXT);

        if (!bill.getObservations().equals(MAX_TEXT)) {
            fail("las observaciones deberian tener una longitud de " + Bill.MAX_LENGTH + " caracteres, pero tienen " + bill.getObservations().length());
        }
    }

    @Test
    public void testBillBreakDownConceptLength() {
        billBreakDown.setConcept(VERY_LARGE_TEXT);

        if (!billBreakDown.getConcept().equals(MAX_TEXT)) {
            fail("los conceptos deberian tener una longitud de " + BillBreakDown.MAX_LENGTH + " caracteres, pero tienen " + billBreakDown.getConcept().length());
        }
    }

    /**
     * Inserción de sólo un pago
     */
    @Test
    public void testCreateBillWithOnlyOnePayment() {
        final BillPayment payment = new BillPayment();
        payment.setAmount(new BigDecimal(1000));
        payment.setExpirationDate(new Date());
        payment.setBill(bill);
        bill.getBillPayment().add(payment);

        if (bill.getBillPayment().size() != 1) {
            fail("la factura deberia tener 1 pago, pero tiene " + bill.getBillPayment().size());
        }
    }

    /**
     * Inserción de tres pagos para comprobar que la factura vence el dia que vence el ultimo pago
     * o cobro
     */
    @Test
    public void testLastPayment() {
        // se crean 3 fechas para los pagos, el 1 de enero, el 1 de julio y el 31 de diciembre de 2000
        final Calendar _2000enero01 = Calendar.getInstance();
        _2000enero01.set(2000, Calendar.JANUARY, 1);

        final Calendar _2000julio01 = Calendar.getInstance();
        _2000julio01.set(2000, Calendar.JULY, 1);

        final Calendar _2000diciembre31 = Calendar.getInstance();
        _2000diciembre31.set(2000, Calendar.DECEMBER, 31);

        // se crean 3 pagos y se les ponen fechas
        final BillPayment firstPayment = new BillPayment();
        firstPayment.setAmount(new BigDecimal(1000));
        firstPayment.setExpirationDate(_2000julio01.getTime());
        firstPayment.setBill(bill);
        bill.getBillPayment().add(firstPayment);

        final BillPayment secondPayment = new BillPayment();
        secondPayment.setAmount(new BigDecimal(1000));
        secondPayment.setExpirationDate(_2000diciembre31.getTime());
        secondPayment.setBill(bill);
        bill.getBillPayment().add(secondPayment);

        final BillPayment thirdPayment = new BillPayment();
        thirdPayment.setAmount(new BigDecimal(1000));
        thirdPayment.setExpirationDate(_2000enero01.getTime());
        thirdPayment.setBill(bill);
        bill.getBillPayment().add(thirdPayment);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFactura = sdf.format(bill.getExpiration());
        String fecha31Diciembre = sdf.format(_2000diciembre31.getTime());

        if (!fechaFactura.equals(fecha31Diciembre)) {
            fail("la factura deberia finalizar el " + fecha31Diciembre + " en vez de el " + fechaFactura);
        }
    }
}
