<template>
  <div>
    <TheNavbar />
    <main>
      <HeroImage />
      <TrustIcons />
      <CallToActionSection />
      <LatestAuctions />
      <CommunityInfoSection />
      <ClientFeedbackSection />
      <NewsletterSignup />
    </main>
  </div>
</template>

<script>
import TheNavbar from './TheNavbar.vue';
import HeroImage from './HeroImage.vue';
import TrustIcons from './TrustIcons.vue';
import CallToActionSection from './CallToActionSection.vue';
import LatestAuctions from './auction-demos/LatestAuctionsContainer.vue';
import CommunityInfoSection from './CommunityInfoSection.vue';
import ClientFeedbackSection from './client-feedback/ClientFeedbackContainer.vue';
import NewsletterSignup from './NewsletterSignup.vue';

export default {
  components: {
    HeroImage,
    TrustIcons,
    CallToActionSection,
    LatestAuctions,
    CommunityInfoSection,
    ClientFeedbackSection,
    NewsletterSignup,
    TheNavbar,
  },
};
</script>