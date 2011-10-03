/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 * Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.mail;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import javax.mail.MessagingException;

public interface MailService {

	/**
	 * Send messages without attachments
	 */
    void send(String to, String subject, String text) throws MessagingException;

	/**
	 * Send messages with physical files as attachments
	 */
    void sendFiles(String to, String subject, String text, Collection<File> attachments) throws MessagingException;
    
	/**
	 * Send messages with virtual files as attachments
	 */
    public void sendOutputStreams(String to, String subject, String text, Map<InputStream, String> attachments) throws MessagingException;

}
